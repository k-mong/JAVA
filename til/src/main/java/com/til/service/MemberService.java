package com.til.service;

import com.til.dto.MemberJoinRequestDto;
import com.til.entity.Member;
import com.til.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return null;
    }

//    @Override
//    public void join(MemberJoinRequestDto memberJoinRequestDto) {
//        if(memberRepository.findByEmail(memberJoinRequestDto.getEmail()).isPresent()){
//            throw new RuntimeException("이미 존재하는 회원 입니다.");
//        }
//        memberJoinRequestDto.bcryptPassword(bCryptPasswordEncoder.encode(memberJoinRequestDto.getPassword()));
//        Member member = memberJoinRequestDto.toEntity();
//        memberRepository.save(member);
//    }
}
