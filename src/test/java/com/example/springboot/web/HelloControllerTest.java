package com.example.springboot.web;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
//여기서는 SpringExtension이라는 스프링 실행자를 사용한다.
@ExtendWith(SpringExtension.class)
//여러 스프링 테스트 어노테이션 중, web에 집중할 수 있는 어노테이션
//@Controller, @ControllerAdvice 등을 사용할 수 있다.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //스프링이 관리하는 빈을 주입받는다.
    @Autowired
    //웹 api를 테스트할 때 사용, 스프링 mvc테스트의 시작점.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        //MockMvc를 통해 /hello 주소로 HTTP GET요청을 한다.
        mvc.perform(get("/hello"))
                //http header의 status를 검증한다.
                .andExpect(status().isOk())
                //응답 본문의 내용을 검증한다.
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        //api 테스트 시 사용될 요청 파라미터 설정, String 값만 허용되기 때문에 문자열로 변경해서 등록
        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                //json 응답값을 필드별로 검증할 수 있는 메소드
                //$를 기준으로 필드명을 명시, $.name은 json 데이터 내에서 key가 name인 값
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
