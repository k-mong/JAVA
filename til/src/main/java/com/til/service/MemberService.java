package com.til.service;

import com.til.dto.MemberJoinRequestDto;
import com.til.dto.MemberLoginDto;
import com.til.entity.Member;
import com.til.entity.Role;
import com.til.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;




    public String join(MemberJoinRequestDto memberJoinRequestDto) {

        if(existByEmail(memberJoinRequestDto.getEmail())) {
            throw new RuntimeException("이미 존재하는 아이디 입니다.");
        }

        System.out.println(">>>>>>>>>>"+ passwordEncoder.encode(memberJoinRequestDto.getPassword()));
        Member member = memberRepository.save(
                Member.builder()
                        .email(memberJoinRequestDto.getEmail())
                        .password(passwordEncoder.encode(memberJoinRequestDto.getPassword()))
                        .phone(memberJoinRequestDto.getPhone())
                        .name(memberJoinRequestDto.getName())
                        .build()
        );
        return "회원가입에 성공하셨습니다.";
    }

    public boolean existByEmail(String email) {

        return memberRepository.existsByEmail(email);
    }

    public String login(MemberLoginDto memberLoginDto) {
        Optional<Member> member = memberRepository.findByEmail(memberLoginDto.getEmail());
        if (!member.isPresent()) {
            throw new RuntimeException("존재하지 않는 아이디 입니다.");
        } else if (!passwordEncoder.matches(memberLoginDto.getPassword(), member.get().getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        return "로그인 완료";
    }


}
