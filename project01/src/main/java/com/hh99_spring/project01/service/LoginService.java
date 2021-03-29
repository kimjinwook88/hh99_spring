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
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		Member member = memberRepository.findByUsername(username);
		return new User(member.getUsername(), member.getPassword(), roles);
	}
	
	public void loadUserByUsernameKaKao(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER_KAKAO"));
		UserDetails userDetails = new User(username, "empty", roles);
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));		
	}
	
	
	//카카오톡 토큰 유효성 검사 (참고 사이트 : https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#get-token-info)
	public int getKakaoTokenCheck(String token, String username) {
		URL url = null;
		HttpURLConnection conn = null;
		int code = 0;
		try {
			url = new URL("https://kapi.kakao.com/v1/user/access_token_info");
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setRequestProperty("Authorization", "Bearer "+token);
			conn.setDoOutput(true);
			conn.disconnect();
			code = conn.getResponseCode();
			if(code == 200 && username != null && username != "") {
				loadUserByUsernameKaKao(username);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		
		return code;
	}


		

}
 



