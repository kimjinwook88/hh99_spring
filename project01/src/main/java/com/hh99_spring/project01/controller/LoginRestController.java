package com.hh99_spring.project01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController("/")
public class LoginRestController {
	
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
	 
}
