package com.jojodu.book.springboot.web.dto;


import com.jojodu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String content;
    private String author;


    //TODO: 생성자에 void가 붙지 않는가? 여기에 롬복 @Builder 어노테이션을 달면 메소드가 이상하게 나온다.
    // 그래서 void를 빼고하면 정상적으로 나온다 무슨 문제이지?
    @Builder
    public PostsUpdateRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }


}
