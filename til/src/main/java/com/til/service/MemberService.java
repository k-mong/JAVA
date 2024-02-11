package com.til.service;

import com.til.dto.MemberJoinRequestDto;

public interface MemberService {
    Long join(MemberJoinRequestDto memberJoinRequestDto);
}
