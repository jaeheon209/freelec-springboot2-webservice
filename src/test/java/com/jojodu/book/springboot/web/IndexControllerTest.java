package com.jojodu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void 메인페이지_로딩() {
        //when
        String body = this.testRestTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스 돈이 된다.");

        // URL 호출시 페이지의 내용이 제대로 호출되는지에 대한 테스트
        // HTML도 결국은 규칙이 있는 문자열 입니다.
        // "/"로 호출했을때, index.mustache에 포함된 코드들이 있는지 확인 하면 된다.
        // 전체 코드를 다 검증할 필요는 없으니, "스피링 부트로 시작하는 웹 서비스" 문자열 포함 확인 비교
    }
}
