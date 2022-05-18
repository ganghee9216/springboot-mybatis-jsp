package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//스프링 부트의 자동설정, Bean 읽기와 생성을 모두 자동으로 설정
//때문에 이 클래스는 항상 프로젝트의 최상위에 위치해야 한다.
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        //내장 was 실행
        SpringApplication.run(Application.class, args);
    }
}
