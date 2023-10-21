package com.playdata.member.service;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.repository.MemberRepository;
import com.playdata.domain.member.request.MemberRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    void save() {
        //given
        String nickname = "name";
        String profileImageUrl = "asd.jpg";
        MemberRequest memberRequest = new MemberRequest(nickname,profileImageUrl);
        //when
        memberService.save(memberRequest);

        //then
        List<Member> all = memberRepository.findAll();
        assertThat(all).hasSize(1);// 레코드가 하나만 저장됐는지 확인
        assertThat(all.get(0).getNickname()).isEqualTo(nickname);// 닉네임 확인
    } //member save 테스트 하려면 Member entity의 id를 GeneratedValue를 해주어야 한다. 그러면 성공
}