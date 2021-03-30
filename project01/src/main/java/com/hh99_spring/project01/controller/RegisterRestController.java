
package com.hh99_spring.project01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hh99_spring.project01.domain.Member;
import com.hh99_spring.project01.domain.MemberRepository;
import com.hh99_spring.project01.domain.MemberRequestDto;
import com.hh99_spring.project01.service.RegisterService;

import lombok.RequiredArgsConstructor;

// 회원가입 Controller
@RequiredArgsConstructor
@RestController
public class RegisterRestController {
    private final RegisterService registerService;
    private final MemberRepository memberRepository;
    
    /*
     * 회원 가입 
     * 등록 전 조건 체크 후 Exception 발생
     * registerService에서 처리
     */
    @PostMapping("/api/register")
    public Member createMember(@RequestBody MemberRequestDto memberRequestDto) {
        Member member = new Member(memberRequestDto);
        registerService.checkName(member);
        registerService.checkPassword(member);
        registerService.checkNameDuplication(member);
        return memberRepository.save(member);
    }
    
    /*
     * 닉네임 중복 체크
     * DB조회로 판단
     */
    @GetMapping("/api/user/{name}")
    public Member checkNm(@PathVariable String name) {
    	return memberRepository.findByUsername(name);
    }
}
