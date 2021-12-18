package com.jojodu.book.springboot.web;

import com.jojodu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 테스트시 JUnit외에 다른 실행자를 실행
// 여기서는 SpringRunner라는 스프링 실행자!
// 스프링 부트와 JUnit의 연걸자 역할
@RunWith(SpringRunner.class)

//WEB에 집중
// Controller, ControllerAdvice 사용가능
// 단 Service, Component, Repository 사용 못함.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean을 주입 받는다.
    private MockMvc mvc;
    // 웹 API를 테스트할 때 사용



    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }


}
