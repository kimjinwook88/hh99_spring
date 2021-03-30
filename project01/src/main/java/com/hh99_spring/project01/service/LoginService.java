package com.hh99_spring.project01.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hh99_spring.project01.domain.Member;
import com.hh99_spring.project01.domain.MemberRepository;

@Service
public class LoginService  implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	/*
	 * Spring Security에서 제공하는 사용자 정보 저장 함수
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		Member member = memberRepository.findByUsername(username);
		return new User(member.getUsername(), member.getPassword(), roles);
	}
	
	/*
	 * 카카오 로그인 후 강제로 spring security 사용자 정보 저장
	 */
	public void loadUserByUsernameKaKao(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER_KAKAO"));
		UserDetails userDetails = new User(username, "empty", roles);
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));		
	}
	
	
	/*
	 * 카카오 토큰 유효성 검사 (참고 사이트 : https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#get-token-info)
	 * 유효한 토큰이라면 spring security 사용하여 강제적으로 로그인 loadUserByUsernameKaKao 함수 참고
	 * 
	 */
	public int getKakaoTokenCheck(String token, String username) {
		URL url = null;
		HttpURLConnection conn = null;
		int code = 0; //reponse code가 200이면 유효 토큰으로 판단후 loadUserByUsernameKaKao를 사용하여 강제 로그인
		return code;
	}


		

}
 



