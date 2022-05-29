package com.example.springbootmybatisjsp.dto.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

public class PostsDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime modifiedDate;

    @Builder
    public PostsDto(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
