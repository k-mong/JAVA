package com.til.dto;

import com.til.entity.Member;
import com.til.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberJoinRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;
    private Role roel;

    public Member toEntity(String encode) {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .role(roel)
                .build();
    }
}
