package com.jojodu.book.springboot.web;

import com.jojodu.book.springboot.domain.posts.Posts;
import com.jojodu.book.springboot.domain.posts.PostsRepository;
import com.jojodu.book.springboot.web.dto.PostsResponseDto;
import com.jojodu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojodu.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// PostsApiController를 테스트하는데 HelloController와 달리
// @WebMvcTest를 사용하지 않았습니다.
// @WebMvcTest의 경우 JPA 기능이 작동하지 않기 때문입니다.
// ㄴ Controller와 ControllerAdvice등 외부 연동과 관련된 부부만 활성화 되니
// ㄴ 지금 같이 JPA 기능까지 한번에 테스트할떄는 @SpringBootTest와 TestRestTemplate를 사용한다.

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort // 로컬 포트가 랜덤하게 지정되는 설정 - 사용하는 이유가 무엇인가?
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired //권장하지 않지만 테스트코드라 사용하는 것인가?
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    //controller test라고 봐도 되나?
    @Test
    public void Posts_등록된다() throws Exception {

        //1.given
        String title = "title!!";
        String content = "conetent!!";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("jaehoen209")
                .build();

        //save controller url
        String url = "http://localhost:" + port + "/api/v1/posts";

        //2.when
        ResponseEntity<Long> responseEntity = restTemplate
                .postForEntity(url, requestDto, Long.class);


        //3.then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = postsRepository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정된다() throws Exception {
        //given
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title1")
                .content("content1")
                .author("author1")
                .build());

        Long updateId = savePosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";
        PostsUpdateRequestDto updateRequestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(updateRequestDto);


        //when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//                .postForEntity(url,updateRequestDto,Long.class);
        // post랑 put이랑 restTemplate를 사용하는 메소드 및 데이터 전달 형테가 다르다
        // ㄴ post의 경우 dto를 바로 던졌고,
        // ㄴ put의 경우 메소드를 며시하고, dto를 httpEntity로 변경하여 던지다.
        // ㄴ dto로 전달하는것과 httpEntity로 전달하는 것은 어떻게 다른가?
        // ㄴ put과 post에서 다른 이유가 무엇인가?


        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L); // 이것은 무엇을 의미하나?

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

    @Test
    public void Posts_가져온다() throws Exception{
        //given
        String title = "title";
        String content = "content";

        Long getId = postsRepository.save(

                Posts.builder()
                .title(title)
                .content(content)
                .author("jaeheon")
                .build()).getId();

        String url = "http://localhost:" + port + "/api/v1/posts/" + getId;

        //when
        ResponseEntity<Posts> responseEntity = restTemplate
                .getForEntity(url,Posts.class);

        //then
        PostsResponseDto postsResponseDto = new PostsResponseDto(responseEntity.getBody());
        assertThat(postsResponseDto.getTitle()).isEqualTo(title);
        assertThat(postsResponseDto.getContent()).isEqualTo(content);
    }


}
