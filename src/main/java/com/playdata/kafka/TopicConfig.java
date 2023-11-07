package com.playdata.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    public final static String STORY = "story"; //내가 발행한 토픽
    public final static String TASK = "task";
    public final static String MEMBER = "member"; //내가 구독한 토픽
    public final static String topicDLT = "member.DLT";

    @Bean
    public NewTopic story() {
        return TopicBuilder
                .name(STORY)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic taskUpdate() {
        return TopicBuilder
                .name(TASK)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic member() {
        return new NewTopic(MEMBER, 1, (short) 1);
    }

    @Bean
    public NewTopic topicDLT() {
        return new NewTopic(topicDLT, 1, (short) 1);
    }
}
