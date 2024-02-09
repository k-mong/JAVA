package com.til.service;

import com.til.entity.Member;
import com.til.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    public Member join(Member member) {
        Optional<Member> findmember = memberRepository.findByEmail(member.getEmail());
        if(findmember.isPresent()){
            throw new RuntimeException("이미 존재하는 회원입니다.");
        }

        return member;

    }

}
