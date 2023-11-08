package com.playdata.kafka;

import com.playdata.domain.article.kafka.ArticleUpdateKafka;
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
public class StoryUpdateProducer {
    private final KafkaTemplate<String, ArticleUpdateKafka> kafkaTemplate;
    @Async
    public void send(ArticleUpdateKafka articleUpdateKafka) {
        CompletableFuture<SendResult<String, ArticleUpdateKafka>> resultCompletableFuture =
                kafkaTemplate.send(TopicConfig.STORY_UPDATE, articleUpdateKafka);

        if(resultCompletableFuture.isCompletedExceptionally()){
            throw new RuntimeException("발행 실패");}

        resultCompletableFuture
                .thenAccept(result ->
                        log.info("send After "
                                + articleUpdateKafka + " "
                                + result.getRecordMetadata()
                                .offset()));
        log.info("send={}",articleUpdateKafka);
    }
}
