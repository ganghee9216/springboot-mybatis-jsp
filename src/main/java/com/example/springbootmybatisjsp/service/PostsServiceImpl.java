package com.example.springbootmybatisjsp.service;

import com.example.springbootmybatisjsp.dto.posts.*;
import com.example.springbootmybatisjsp.mapper.PostsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostsMapper postsMapper;

    //트랜잭션 기능 적용, 메소드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.
    @Transactional
    public Long save(PostsDto postsDto){
        return postsMapper.postsSave(postsDto);
    }

    @Transactional
    public Long update(Long id, PostsDto postsDto){
        PostsDto posts = postsMapper.findById(id);
        posts.update(postsDto.getTitle(), postsDto.getContent());
        postsMapper.postsUpdate(posts);

        return posts.getId();
    }
    public PostsDto findById(Long id){
        PostsDto entity = postsMapper.findById(id);

        return entity;
    }

    //(readOnly = true) -> 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회속도 개선
    //등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용
    @Transactional(readOnly = true)
    public List<PostsDto> findAllDesc(){
        return postsMapper.findAllDesc();
    }

    @Transactional
    public void delete(Long id){
        PostsDto postsDto = postsMapper.findById(id);
        postsMapper.delete(postsDto.getId());
    }
}
