package com.hh99_spring.project01.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hh99_spring.project01.domain.AritcleRequestDto;
import com.hh99_spring.project01.domain.Article;
import com.hh99_spring.project01.domain.ArticleRepository;
import com.hh99_spring.project01.service.ArticleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ArticleRestController {
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    

    @GetMapping("/api/articles")
    public List<Article> getArticle() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }

    @RequestMapping(value = "/detail" , method = RequestMethod.GET)
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail.html");
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


}
