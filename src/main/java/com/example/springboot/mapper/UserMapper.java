package com.example.springboot.mapper;

import com.example.springboot.dto.posts.PostsDto;
import com.example.springboot.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDto> findByEmail(String email);

    void save(UserDto userDto);

    UserDto findUserById(Long id);

}
