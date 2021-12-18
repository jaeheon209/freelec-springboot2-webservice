package com.jojodu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 해당 클래스를 상속할 경우 필드들을 칼럼으로 인식하도록 합니다.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity에 Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될때 시간이 자동 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.
    private LocalDateTime modifiedDate;

}
// Java에서 문제가있던 Date와 Calendar 클래스의
// 문제를 해결한 클래스가 LocalDateTime Java8이라면 사용을 권장한다.

// 문제점
// 1. 불변객체가아니다. (멀티스레드환경에서 언제든 문제 발생여지가있음)
// 2. calendar는 월 값이 잘못 설계됨 OCTOBER의 숫자가 9임.
// 기존에는 JodaTime이라는 오픈소스로 문제점을 회피, java8은 LocalDate를 통해 해결
// https://d2.naver.com/helloworld/654609
