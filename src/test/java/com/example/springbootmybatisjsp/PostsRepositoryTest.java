package com.example.springbootmybatisjsp;


import com.example.springbootmybatisjsp.dto.posts.PostsDto;
import com.example.springbootmybatisjsp.mapper.PostsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsMapper postsMapper;

    @Test
    public void 게시글저장_불러오기(){
        //필드값 정의
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsMapper.postsSave(PostsDto.builder().title(title).content(content).author("qoemfqhdl@naver.com").build());

        //객체 생성
        //테이블 posts에 있는 모든 데이터 조회
        List<PostsDto> postsList = postsMapper.findAllDesc();

        //검증
        PostsDto posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
