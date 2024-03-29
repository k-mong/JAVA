package com.til.dto;

import com.til.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;
    private Role role;


}
