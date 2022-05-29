package com.example.springbootmybatisjsp.service;


import com.example.springbootmybatisjsp.dto.posts.*;

import java.util.List;

public interface PostsService {

    public PostsDto findById(Long id);

    public List<PostsDto> findAllDesc();

    public Long save(PostsDto postsDto);

    public Long update(Long id, PostsDto postsDto);

    public void delete(Long id);
}
