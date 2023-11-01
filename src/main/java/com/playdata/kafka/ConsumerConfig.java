package com.playdata.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@RequiredArgsConstructor
public class ConsumerConfig {
    private final KafkaProperties kafkaProperties;

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> operations) {
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(operations), new FixedBackOff(1000L, 5)
        );
    }


}
