package com.til.api;

import com.til.dto.MemberJoinRequestDto;
import com.til.dto.MemberLoginDto;
import com.til.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        System.out.println("들어옴");
        String result = memberService.join(memberJoinRequestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody MemberLoginDto memberLoginDto) {
        String result = memberService.login(memberLoginDto);
        return ResponseEntity.ok(result);
    }

}
