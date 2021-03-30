package com.hh99_spring.project01.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hh99_spring.project01.domain.Member;
import com.hh99_spring.project01.domain.MemberRequestDto;

@RunWith(SpringRunner.class) 
@SpringBootTest 
public class RegisterTest {
	
	@Autowired
	private RegisterService registerService;
    
	/*
	 * - 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 이루어져 있어야 합니다. 
	 * - 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`합니다. 
	 * - 비밀번호 확인은 비밀번호와 정확하게 일치해야 합니다. -
	 * - 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지가 발생합니다.
	 */
	@Test
	public void test() {
		
	}
}
