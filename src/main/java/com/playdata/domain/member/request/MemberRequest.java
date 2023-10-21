package com.playdata.domain.member.request;

import com.playdata.domain.member.entity.Member;

public record MemberRequest(String nickname, String profileImageUrl)  {

    public Member ToEntity() {
        return Member.builder()
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}