package com.jojodu.book.springboot.domain.posts;

import com.jojodu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


// lombok 어노테이션 -필수가 아니라 class와 멀리 설정
@Getter
@NoArgsConstructor // 기본 생성자 자동추가 : public Posts() {}와 같은 효과

// JPA 어노테이션 - 테이블과 링크될 클래스임을 표현
// ex) SalesManager.java -> sales_manager table
// 실제 DB와 매핑되는 Entity클래스
// JPA를 사용하면 DB 데이터에 작업할 경우 실제 쿼리보다는 해당 클래스에서 수정하여 작업
@Entity
public class Posts extends BaseTimeEntity {
    @Id // 테이블의 PK 필드를 표시
    // PK생성 규칙을 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 스프링부트 2.0에서 해당 표기는 auto_increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //Column을 사용하지 않아도 클래스의 필드는 모두 컬럼이 된다.
    // 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있을 경우 사용
    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성 - 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
//Entity클래스에서는 절대 Setter메소드를 만들지 않는다.
//대신, 해당 필드의 값이 변경이 필요하면 명확히 그 목적과 의도를 나타낼수 있는 메소드를 추가해야한다.
