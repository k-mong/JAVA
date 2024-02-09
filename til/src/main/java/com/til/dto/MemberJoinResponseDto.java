package com.til.dto;

import com.til.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MemberJoinResponseDto {

    private final String email;
    private final String name;

    public MemberJoinResponseDto(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
    }


}
