package com.til.api;

import com.til.dto.MemberJoinRequestDto;
import com.til.repository.MemberRepository;
import com.til.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class MemberApiController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("join")
    public String join(@RequestBody @Valid MemberJoinRequestDto memberReqDto) {
        log.info("들어옴");
        memberService.join(memberReqDto);
        return "회원가입이 완료되었습니다.";
    }


//    @PostMapping("/join")
//    public Long join(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
//        System.out.println("Join 실행");
//        Member join = memberService.join(
//        MemberJoinResponseDto memberJoinResponseDto = new MemberJoinResponseDto(join);
//        return join.getId();
//    }
}
