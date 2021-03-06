package com.example.springbootmybatisjsp.mapper;

import com.example.springbootmybatisjsp.dto.posts.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostsMapper {
    PostsDto findById(Long id);
    List<PostsDto> findAllDesc();
    Long postsSave(PostsDto postsDto);
    Long postsUpdate(PostsDto postsDto);
    void delete(Long id);
}
