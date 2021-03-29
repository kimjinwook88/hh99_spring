package com.hh99_spring.project01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hh99_spring.project01.service.LoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController("/")
public class LoginRestController {

	private final LoginService loginService;
	
	 @RequestMapping(value="/") 
     public ModelAndView index() {
		 ModelAndView modelAndView = new ModelAndView("redirect:loginForm"); 
		 return modelAndView;
     }

	 @RequestMapping(value="/loginForm") 
	  public ModelAndView loginForm() {
	      ModelAndView modelAndView = new ModelAndView("loginForm"); 
		 return modelAndView;
	  }
	 
	 @RequestMapping(value="/loginFail")
	  public ModelAndView loginFail() {
	     ModelAndView modelAndView = new ModelAndView("loginFail");
		 return modelAndView;
	  }
	 
	 @GetMapping(value="/kakao/token")
	  public int kakaoToken(@RequestParam(value = "token") String token, @RequestParam(value = "username") String username) {
		 return loginService.getKakaoTokenCheck(token, username);
	  }
}
