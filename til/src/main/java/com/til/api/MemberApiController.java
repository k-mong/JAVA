package com.til.api;

import com.til.dto.MemberJoinRequestDto;
import com.til.dto.MemberJoinResponseDto;
import com.til.entity.Member;
import com.til.entity.Role;
import com.til.repository.MemberRepository;
import com.til.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class MemberApiController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @PostMapping("/join")
//    public Long join(@RequestBody @Valid Map<String, String> user) {
//        log.info("들어옴");
//        return memberRepository.save(Member.builder()
//                .email(user.get("email"))
//                .password(bCryptPasswordEncoder.encode(user.get("password")))
//                .name(user.get("name"))
//                .phone(user.get("phone"))
//                .role(Role.valueOf(user.get("role")))
//                .build()).getId();
//
//    }


    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        System.out.println("들어옴");
        String result = memberService.join(memberJoinRequestDto);
        return ResponseEntity.ok(result);
    }
}
