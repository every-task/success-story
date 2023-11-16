package com.playdata.kafka;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.kafka.MemberKafkaData;
import com.playdata.domain.member.repository.MemberRepository;
import com.playdata.exception.NotCorrectMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberConsumer {
    private final MemberRepository memberRepository;

    @KafkaListener(topics = TopicConfig.MEMBER)
    public void listen(MemberKafkaData data) {
        if (data.action().equals("CREATE") || (data.action().equals("UPDATE"))) {
            memberRepository.save(data.toEntity());
        } else {
            Member member = memberRepository.findById(UUID.fromString(data.id()))
                    .orElseThrow(() -> new NotCorrectMemberException("Not Correct Member, memberId = {%s}".formatted(data.id())));
            member.delete();
        }
    }

    @KafkaListener(topics = TopicConfig.topicDLT)
    public void dltListen(byte[] in) {
        System.out.println("dltListen" + new String(in)); //수정 예정 아직 뭐로 할지 결정하지 못함
    }
}
