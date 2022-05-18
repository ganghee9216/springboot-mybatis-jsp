package com.example.springboot.web.dto;

import org.junit.jupiter.api.Test;
//Junit의 asserThat이 아닌 assertj의 것을 사용
//CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않고, 자동완성이 좀 더 확실하게 지원된다.
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //assertj라는 테스트 검증 라이브러리의 검증 메소드, 검증하고 싶은 대상을 메소드 인자로 받는다.
        //메소드 체이닝이 지원되어 메소드를 이어서 사용 가능
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
