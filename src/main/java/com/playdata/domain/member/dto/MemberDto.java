package com.playdata.domain.member.dto;

import com.playdata.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private UUID id;
    private String nickname;
    private String profileImageUrl;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.profileImageUrl = member.getProfileImageUrl();
    }
}