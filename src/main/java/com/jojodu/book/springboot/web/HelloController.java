package com.jojodu.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//컨트롤러를 JSON으로 반환하는 컨트롤러로 만들어 준다.
// 예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할수 있게 해준다 생각.
@RestController
public class HelloController {

    // HTTP Method GET 요청을 받을 수있는 API를 만들어준다.
    // 이 프로젝트에 /hello로 요청이 오면 문자열을 hello로 반환하는 기능을 가짐.
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
