package com.playdata.kafka;

import com.playdata.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberConsumer {
    private final MemberService memberService;

//    @KafkaListener(topics = TopicConfig.member)
//    public void listen(String msg) {
//        System.out.println("consumer : "+msg);
//    }
}
