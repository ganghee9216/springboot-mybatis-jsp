package com.example.springboot.controller;

import com.example.springboot.dto.posts.PostsDto;
import com.example.springboot.service.PostsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//생성자로 Bean을 주입받는것이 가장 권장되는 방식
//롬복의 이 어노테이션이 생성자를 대신 생성해준다.
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsServiceImpl postsServiceImpl;

    @PostMapping("api/v1/posts")
    //바디 내용을 통째로 자바 객체로 변환해서 매핑된 메소드 파라미터로 전달해준다.
    public Long postsSave(@RequestBody PostsDto postsDto){
        return postsServiceImpl.save(postsDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    //@PathVariable : 메소드 인자에 사용되어 URL템플릿 변수의 값을 메소드 인자로 할당하는데 사용됨
    public Long postsUpdate(@PathVariable Long id, @RequestBody PostsDto postsDto){
        return postsServiceImpl.update(id, postsDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsDto findById(@PathVariable Long id){
        return postsServiceImpl.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsServiceImpl.delete(id);
        return id;
    }
}
