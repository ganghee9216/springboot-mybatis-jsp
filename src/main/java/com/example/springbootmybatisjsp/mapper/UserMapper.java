package com.example.springbootmybatisjsp.mapper;

import com.example.springbootmybatisjsp.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDto> findByEmail(String email);
    void save(UserDto userDto);
    UserDto findUserById(Long id);
}
