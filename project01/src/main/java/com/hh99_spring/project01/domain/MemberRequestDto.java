package com.hh99_spring.project01.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberRequestDto {
    private String username;
    private String password;
    private String passwordNen;
    private String passwordConfirm;
}
