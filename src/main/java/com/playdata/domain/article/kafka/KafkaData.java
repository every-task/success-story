package com.playdata.domain.article.kafka;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaData<T> {
    private T data;
    private KafkaAction action;

    public static <T>KafkaData<T> of(T data, KafkaAction action) {
        return KafkaData.<T>builder()
                .data(data)
                .action(action)
                .build();
    }
}
