package com.playdata.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    public final static String success = "success-topic";

    @Bean
    public NewTopic success() {
//        return new NewTopic(topic1, 1, (short) 1);
        return TopicBuilder
                .name(success)
                .partitions(1)
                .replicas(1)
                .build();
    }


}
