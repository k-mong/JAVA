package com.til.service.Impl;

import com.til.dto.MemberJoinRequestDto;
import com.til.entity.Member;
import com.til.repository.MemberRepository;
import com.til.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Long join(MemberJoinRequestDto memberJoinRequestDto) {
        if(memberRepository.findByEmail(memberJoinRequestDto.getEmail()).isPresent()){
            throw new RuntimeException("이미 존재하는 회원 입니다.");
        }
        memberJoinRequestDto.bencryptPassword(bCryptPasswordEncoder.encode(memberJoinRequestDto.getPassword()));
        Member member = memberJoinRequestDto.toEntity();
        memberRepository.save(member);
        return member.getId();
    }
}
