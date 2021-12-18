package com.jojodu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 어노테이션 활성화

// 스프링부트 자동설정, 스프링 Bean 읽기와 생성 모두 자동 설정
// 특히 @SpringBootApplication이 있는 위치부터 설정을 읽어 가기 때문에
// 항상 프로젝트의 최상단에 위치해야한다.
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // SpringApplication.run으로 인해 내장 WAS를 실행
        // 이렇게 되면 서버에 톰캣을 설치팔 필요가 없게되고
        // 스프링 부트로 만들어진 Jar파일로 실행하면 된다.
        SpringApplication.run(Application.class, args);

        // 스프링 부트에서는 내장 WAS를 사용하는 것을 권장
        // '언제 어디서나 같은 환경에서 스프링 부트를 배포' 할 수 있기 때문
        // 외장 WAS를 사용하면 모든 서버는 WAS의 종류, 버전, 설정을 일치.
        // 서버가 추가될떄마다 해야하면 실수여지도 많고 시간도 오래걸림.
        // 내장 WAS 성능이슈? -> 필자의 회사는 높은 트래픽- 모드 스프링 부트로 운영함.

    }
}
