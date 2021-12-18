package com.jojodu.book.springboot.web.dto;

import com.jojodu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    // domain posts의 Entity클래스와 같은 내용인데 여기는 동일하게 만들어야하나?
    // 어떤 이유료 해야하는건기?
    // ㄴ 절대로 Entity 클래스를 Request/Response 클래스로 사용하면 안된다.
    // ㄴ Entity 클래스는 DB와 맞닿은 핵심 클래스이다.
    // ㄴ Entity 클래스를 기준으로 테이블이 생성, 스키마가 변경된다.
    // ㄴ 화면 변경은 사소한 기능 변경인데, 이를 위해 테이블과 연결된 Entity 클래스를 변경하는 것은 너무 큰 변경이다.
    // ㄴ 수많은 서비스 클래스나 비즈니스 로직들이 Entity 클래스를 기준으로 동작하기때문에 변경되면 여러 클래스에 영향을 끼치지만
    // ㄴ Request/Response용 Dto는 View를 위한 클래스라 정말 자주 변경이 필요
    // * View Layer와 DB Layer의 역할 분리를 철저하게 하는게 좋다.
    // + 실제로 Controller에서 결괏값으로 여러 테이블을 조인해줘야할 경우가 빈번, Entity클래스만으로 표현하기 어려운 경우가 많다.

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Dto -> Entity로!
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
