package com.hh99_spring.project01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Member extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String passwordNen;
    
    @Column(nullable = false)
    private String passwordConfirm;
    
    public Member(MemberRequestDto memberRequestDto){
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //암호화 
    	
        this.username = memberRequestDto.getUsername();
        this.password = encoder.encode(memberRequestDto.getPassword());
        this.passwordNen = memberRequestDto.getPasswordNen();
        this.passwordConfirm = memberRequestDto.getPasswordConfirm();
    }
}
