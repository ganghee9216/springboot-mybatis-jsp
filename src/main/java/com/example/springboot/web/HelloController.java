package com.example.springboot.web;

import com.example.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//json을 반환하는 컨트롤러로 만들어준다.
@RestController
public class HelloController {

    //http method인 get의 요청을 받을 수 있는 api를 만들어준다.
    // /hello 요청이 오면 문자열 hello를 반환하는 기능
   @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    //@RequestParam : 외부에서 api로 넘긴 파라미터를 가져오는 어노테이션
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name,
                                             @RequestParam("amount") int amount){
       return new HelloResponseDto(name, amount);
    }
}
