package com.example.springboot.service;


import com.example.springboot.dto.posts.*;

import java.util.List;

public interface PostsService {

    public PostsDto findById(Long id);

    public List<PostsDto> findAllDesc();

    public Long save(PostsDto postsDto);

    public Long update(Long id, PostsDto postsDto);

    public void delete(Long id);
}
