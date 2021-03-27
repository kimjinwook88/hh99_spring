package com.hh99_spring.project01.etc;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hh99_spring.project01.service.LoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final LoginService loginService;
  
  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
  }

  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  http
      .authorizeRequests()
      	  //.antMatchers("/detail*").hasAnyRole()
      	  .antMatchers("/**").permitAll()
          .anyRequest().permitAll()
          .and()
      .formLogin()
          .loginPage("/loginForm")
          .loginProcessingUrl("/login")
          .failureUrl("/loginFail")
          .permitAll()
          .defaultSuccessUrl("/index.html")
          .and()
      .logout()
      	.logoutUrl("/logout")
      	.logoutSuccessUrl("/")
      	.invalidateHttpSession(true)
          .permitAll();
	  
	  http.cors().and();
	  http.csrf().disable();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());;
   }
}