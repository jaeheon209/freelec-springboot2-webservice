package com.jojodu.book.springboot.service.posts;

import com.jojodu.book.springboot.domain.posts.Posts;
import com.jojodu.book.springboot.domain.posts.PostsRepository;
import com.jojodu.book.springboot.web.dto.PostsListResponseDto;
import com.jojodu.book.springboot.web.dto.PostsResponseDto;
import com.jojodu.book.springboot.web.dto.PostsUpdateRequestDto;
import com.jojodu.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

// 스프링에서 Bean을 주입 받는 방식은
// 1) @Autowired - 권장하지 않는다. (why? 계속 수정해줘야하는 동일한 이유인가?)
// 2) setter
// 3) 생성자
// 즉, 생성자로 Bean 객체를 받도록 하면 @Autowired와 동일한 효과
// @RequiredArgsConstructor에서 해결해준다. (final 및 notnull 클래스변수들을 생성자로 생성)
// 생성자를 사용하게되면 클래스의 의존성이 변경될때마다 생성자 코드르 계속 수정하는 번거로움이 있어
// 롬복 어노테이션을 사용한다.

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // update 기능에서 DB에 쿼리를 날리는 부분이 없습니다.
    // 이게 가능한 이유는 JPA의 영속성 컨텍스트 때문.
    // ㄴ 영속성 컨텍스트 - 엔티티를 영구 저장하는 환경입니다. (일종의 논리적 개념_
    // ㄴ JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
    // JPA의 엔티티 매니저가 활성화된 상태로(Spring Data Jpa를 쓰면 기본 옵션)
    // ㄴ 트랜잭션(@Transactional) 안에서 DB 데이터를 가지고오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
    // ㄴ 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
    // - 이것은 더티 체킹이라고한다. (https://jojoldu.tistory.com/415)
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 ID가 없습니다."));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 ID가 없습니다."));
        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // == .map(posts -> new PostsResponseDto(posts)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 ID가 없습니다. (id : " + id + ")"));
        postsRepository.delete(entity);
    }
}
