package com.example.springbootmybatisjsp.config.auth.dto;

import com.example.springbootmybatisjsp.dto.user.UserDto;
import lombok.Getter;

import java.io.Serializable;

@Getter
//SessionUser에는 인증된 사용자 정보만 필요하기 때문에 name,email,picture만 필드로 선언한다.
public class SessionUser implements Serializable {
    private static final long serialVersionUID = 3620755323511438014L;
    private Long id;
    private String name;
    private String email;
    private String picture;

    public SessionUser(UserDto user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
