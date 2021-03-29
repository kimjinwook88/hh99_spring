package com.hh99_spring.project01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Reply extends Timestamped{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long article_id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true)
    private String contents;

    public Reply(ReplyRequestDto replyRequestDto){
        this.id = replyRequestDto.getId();
        this.article_id = replyRequestDto.getArticle_id();
        this.username = replyRequestDto.getUsername();
        this.contents = replyRequestDto.getContents();
    }
    
    public void update(ReplyRequestDto replyRequestDto){
	  this.article_id = replyRequestDto.getArticle_id();
      this.username = replyRequestDto.getUsername();
      this.contents = replyRequestDto.getContents();
    }

}
