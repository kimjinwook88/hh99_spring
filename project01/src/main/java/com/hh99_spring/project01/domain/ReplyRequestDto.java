package com.hh99_spring.project01.domain;

import lombok.Getter;

@Getter
public class ReplyRequestDto {
    private Long id;
    private Long article_id;
    private String username;
    private String contents;
}
