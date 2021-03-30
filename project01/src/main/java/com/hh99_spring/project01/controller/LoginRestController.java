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
	
	 /*
	 * 로그인 페이지로 redirect (기본페이지 설정)
	 */
	 @RequestMapping(value="/") 
     public ModelAndView index() {
		 return null;
     }
	 
	 /*
	 * 로그인 페이지 이동 
	 */
	 @RequestMapping(value="/loginForm") 
	  public ModelAndView loginForm() {
		 return null;
	  }
	 
	 /*
	 * 로그인 실패시 이동할 페이지 설정 (spring security 설정 - WebSecurityConfig class)
	 */
	 @RequestMapping(value="/loginFail")
	  public ModelAndView loginFail() {
	     return null;
	  }
	 
	 
	 /*
	 * 카카오 토큰 유효성 검증
	 */
	 @GetMapping(value="/kakao/token")
	  public int kakaoToken(@RequestParam(value = "token") String token, @RequestParam(value = "username") String username) {
		 return 0;
	  }
}
