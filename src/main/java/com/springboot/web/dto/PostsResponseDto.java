package com.springboot.web.dto;

import com.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Posts entity;

    public PostsResponseDto(Posts entity){

        this.entity = entity;
    }
}
