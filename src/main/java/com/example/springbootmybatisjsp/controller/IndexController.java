package com.example.springbootmybatisjsp.controller;

import com.example.springbootmybatisjsp.config.auth.LoginUser;
import com.example.springbootmybatisjsp.config.auth.dto.SessionUser;
import com.example.springbootmybatisjsp.dto.posts.PostsDto;
import com.example.springbootmybatisjsp.mapper.UserMapper;
import com.example.springbootmybatisjsp.service.PostsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {


    private final PostsServiceImpl postsServiceImpl;
    private final UserMapper userMapper;

    @GetMapping("/")
    //기존에 httpSession.getAttribute("user")로 가져오던 세션 정보 값 개선. 어노테이션화 시킴
    //이제 어느 컨트롤러든 @LoginUser만 사용하면 세션 정보를 가져올 수 있다.
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsServiceImpl.findAllDesc());
        //세션에 저장된 값이 있을 때만 model에 userName으로 등록한다.
        if(user != null){
            model.addAttribute("user", user);
        }
        return "index";
    }
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsDto dto = postsServiceImpl.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
