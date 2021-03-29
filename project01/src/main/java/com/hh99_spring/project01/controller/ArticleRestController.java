package com.hh99_spring.project01.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hh99_spring.project01.domain.AritcleRequestDto;
import com.hh99_spring.project01.domain.Article;
import com.hh99_spring.project01.domain.ArticleRepository;
import com.hh99_spring.project01.domain.Reply;
import com.hh99_spring.project01.domain.ReplyRepository;
import com.hh99_spring.project01.domain.ReplyRequestDto;
import com.hh99_spring.project01.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ArticleRestController {
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;
    
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView getIndex() {
    	ModelAndView modelAndView = new ModelAndView("index");
    	return modelAndView;
    }

    @GetMapping("/api/articles")
    public List<Article> getArticle() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }

    @RequestMapping(value = "/detail" , method = RequestMethod.GET)
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView("detail");
        return modelAndView;
    }

    @GetMapping("/api/detail/{id}")
    public Article getDetail (@PathVariable Long id){
        return articleRepository.findById(id).orElseThrow(()->new IllegalArgumentException("null"));
    }

    @PostMapping("/api/articles")
    public Article createArticle(@RequestBody AritcleRequestDto aritcleRequestDto) {
        Article article = new Article(aritcleRequestDto);
        return articleRepository.save(article);
    }

    @PutMapping("/api/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody AritcleRequestDto aritcleRequestDto) {
        return articleService.update(id, aritcleRequestDto);
    }

    @DeleteMapping("/api/articles/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return id;
    }
    
    @GetMapping("/api/reply")
    public List<Reply> getReply(@RequestParam(value = "article_id") Long article_id) {
    	return replyRepository.findByArticleIdOrderByModifiredAtDesc(article_id);
    }
 
 	@PostMapping("/api/reply")
    public Reply createReply(@RequestBody ReplyRequestDto replyRequestDto) {
    	Reply reply = new Reply(replyRequestDto);
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	Object principal = auth.getPrincipal();
    	if(principal.equals("anonymousUser")) {
    		throw new IllegalArgumentException("로그인 하지 않은 사용자 입니다."); 
    	}
        return replyRepository.save(reply);
    }
    
	@PutMapping("/api/reply/{id}")
	public Long updateReply(@PathVariable Long id, @RequestBody ReplyRequestDto replyRequestDto) {
		return articleService.updateReply(id, replyRequestDto);
	}

	@DeleteMapping("/api/reply/{id}")
	public Long deleteReply(@PathVariable Long id) {
		replyRepository.deleteById(id);
		return id;
	}
    
}
