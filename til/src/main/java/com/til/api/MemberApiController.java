package com.til.api;

import com.til.dto.MemberJoinRequestDto;
import com.til.dto.MemberJoinResponseDto;
import com.til.entity.Member;
import com.til.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/join")
    public MemberJoinResponseDto join(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        Member join = memberService.join(memberJoinRequestDto.toEntity(bCryptPasswordEncoder.encode(memberJoinRequestDto.getPassword())));
        MemberJoinResponseDto memberJoinResponseDto = new MemberJoinResponseDto(join);
        return memberJoinResponseDto;
    }
}
