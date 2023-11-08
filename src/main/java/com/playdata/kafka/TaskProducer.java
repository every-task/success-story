package com.playdata.kafka;

import com.playdata.domain.task.kafka.TaskUpdateKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskProducer {
    private final KafkaTemplate<String, TaskUpdateKafka> kafkaTemplate;
    @Async
    public void send(TaskUpdateKafka taskUpdateKafka) {
        CompletableFuture<SendResult<String, TaskUpdateKafka>> resultCompletableFuture =
                kafkaTemplate.send(TopicConfig.TASK, taskUpdateKafka);

        if(resultCompletableFuture.isCompletedExceptionally()){
            throw new RuntimeException("발행 실패");
        }

        resultCompletableFuture
                .thenAccept(result ->
                        log.info("send After "
                                + taskUpdateKafka + " "
                                + result.getRecordMetadata()
                                .offset()));
        log.info("send={}",taskUpdateKafka);
    }
}
