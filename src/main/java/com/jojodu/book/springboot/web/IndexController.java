package com.jojodu.book.springboot.web;

import com.jojodu.book.springboot.service.posts.PostsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환 할때,
    // 앞위 경로와 (src/main/resources/templates 머스테치의 default 경로)
    // 뒤의 파일 확장자는 자동으로 지정된다. (~.mustache)
    // 최종 반환 값은 : src/main/resources/templates/index.mustache 로 전달.
    // View Resolver가 처리하게 된다.
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,
                              Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "posts-update";
    }

    @GetMapping("/posts/read/{id}")
    public String postsRead(@PathVariable Long id,
                            Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "posts-read";
    }

}
