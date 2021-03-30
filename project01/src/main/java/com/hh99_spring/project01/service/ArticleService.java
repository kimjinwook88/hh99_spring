package com.hh99_spring.project01.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hh99_spring.project01.domain.AritcleRequestDto;
import com.hh99_spring.project01.domain.Article;
import com.hh99_spring.project01.domain.ArticleRepository;
import com.hh99_spring.project01.domain.Reply;
import com.hh99_spring.project01.domain.ReplyRepository;
import com.hh99_spring.project01.domain.ReplyRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ArticleService {

	private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public Long update(Long id, AritcleRequestDto aritcleRequestDto){
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        article.update(aritcleRequestDto);
        return article.getId();
    }
    
    //댓글 업데이트 로직
    @Transactional
    public Long updateReply(Long id, ReplyRequestDto replyRequestDto){
        return 0L;
    }
}
