package com.hh99_spring.project01.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hh99_spring.project01.domain.Member;
import com.hh99_spring.project01.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RegisterService {
	
	private final MemberRepository memberRepository;
	
	/*
	 * - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 이루어져 있어야 합니다. 
	 * - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`합니다. 
	 * - 비밀번호 확인은 비밀번호와 정확하게 일치해야 합니다. -
	 * - 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지가 발생합니다.
	 */
	
	// 닉네임 체크 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`
    @Transactional
    public String checkName(Member member){
       String pattern = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{3,20}$"; // 영문, 숫자
       String username = member.getUsername();
       String errMsg = "";
       if(username.isEmpty()) {
    	   errMsg = "닉네임을 입력해 주세요.";
    	   throw new IllegalArgumentException("닉네임을 입력해 주세요.");
       }
       
       Matcher match = Pattern.compile(pattern).matcher(username);
       if(!match.find()) {
    	   errMsg = "닉네임은 숫자와 영문자 조합으로 3~10자리를 사용해야 합니다.";
    	   throw new IllegalArgumentException("닉네임은 숫자와 영문자 조합으로 3~20자리를 사용해야 합니다.");
       }
       
       return errMsg;
    }
    
    
    /*
     * 	- 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`합니다. 
	 * - 비밀번호 확인은 비밀번호와 정확하게 일치해야 합니다.
     */
    @Transactional
    public void checkPassword(Member member){
    	//암호화 되지 않은 비밀번호로 비교
    	String username = member.getUsername();
        String passwordNen = member.getPasswordNen();
        String passwordConfirm = member.getPasswordConfirm();
       
        if(passwordNen.isEmpty() || passwordConfirm.isEmpty()) {
        	throw new IllegalArgumentException("패스워드를 입력해 주세요.");
        }
        
        if(passwordNen.length() < 4 || passwordNen.length() > 20) {
        	throw new IllegalArgumentException("비밀번호는  4~20자리를 사용해야 합니다.");
        }
        
        if(passwordNen.indexOf(username) != -1) {
        	throw new IllegalArgumentException("비밀번호에 닉네임을 포함할 수 없습니다.");
        }
        
        if(!passwordNen.equals(passwordConfirm)) {
        	throw new IllegalArgumentException("패스워드가 일치하지 않습니다!");
        }
     }
    
    // - 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지가 발생합니다.
    @Transactional
    public void checkNameDuplication(Member member){
    	Member m = memberRepository.findByUsername(member.getUsername());
    	if(m != null) {
    		throw new DuplicateKeyException("중복된 닉네임 입니다.");
    	}
    	
    }
    
}
