package com.example.springbootmybatisjsp;

import com.example.springbootmybatisjsp.dto.posts.PostsDto;
import com.example.springbootmybatisjsp.mapper.PostsMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
//호스트가 사용하지 않는 랜덤 포트를 사용하겠다는 의미
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsMapper postsMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    //인증된 모의 사용자를 만들어 사용. roles에 권한을 추가할수있다.
    @WithMockUser(roles = "USER")
    public void Posts_등록된다() throws Exception {
        //필드값 생성
        String title = "title";
        String content = "content";
        PostsDto postsDto = PostsDto.builder().title(title).content(content).author("author").build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //객체 생성
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postsDto)))
                .andExpect(status().isOk());

        //검증
        List<PostsDto> all = postsMapper.findAllDesc();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void Posts_수정된다() throws Exception {
        //필드값 생성
        PostsDto postsDto = PostsDto.builder().title("title").content("content").author("author").build();

        postsMapper.postsSave(postsDto);

        Long updateId = postsDto.getId();

        String expectedTitle = "title2";
        String expectedContent = "content2";

        postsDto = PostsDto.builder().title(expectedTitle).content(expectedContent).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        //HttpEntity<PostsDto> requestEntity = new HttpEntity<>(postsDto);

        //객체 생성
        mvc.perform(put(url)
                        //json 타입으로 지정
                        .contentType(MediaType.APPLICATION_JSON)
                        //postsDto를 String 형태로 변환
                        .content(new ObjectMapper().writeValueAsString(postsDto)))
                .andExpect(status().isOk());
        //검증
        List<PostsDto> all = postsMapper.findAllDesc();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        postsMapper.delete(updateId);
    }
}
