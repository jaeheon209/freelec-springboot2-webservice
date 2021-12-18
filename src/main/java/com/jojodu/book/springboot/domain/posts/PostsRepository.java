package com.jojodu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//보통 MyBatis에서 DAO라고 불리는 DBLayer 접근자이며,
//JPA에선 Repository라고 부르며 인터페이스로 생성.
//단순이 인터페이스를 생성후 JpaRepository<Entity 클래스, PK 타입>를 상속하면
//기본 CRUD 메소드가 자동 생성된다.
//@Repository를 추가할필요는 없으며
//주의할점은 Entity클래스와 기본 Entity Repository는 함께 위치해야한다는 점이다.
// Entity클래스는 기본 Repository 없이는 제대로 역할을 할수가 없다.
public interface PostsRepository extends JpaRepository<Posts,Long> {

    //아래는 Query 어노테이션으로 실제 SQL을 사용 가능을 보여주기 위한 예시이다.
    // Entity클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용한다.
    // 대포적으로 querydsl, jooq, MyBatis등이 있는데,
    // querydsl을 추천한다. 많이사용함.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
