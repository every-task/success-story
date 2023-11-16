package com.playdata.kafka;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleUpdateKafka;
import com.playdata.domain.article.kafka.KafkaAction;
import com.playdata.domain.article.kafka.KafkaData;
import com.playdata.exception.PublishingFailedException;
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
public class ArticleProducer {
    private final KafkaTemplate<String, KafkaData> kafkaTemplate;

    @Async
    public void sendUpdate(ArticleUpdateKafka articleUpdateKafka) {
        send(articleUpdateKafka, KafkaAction.UPDATE);
    }

    @Async
    public void sendDelete(ArticleUpdateKafka articleUpdateKafka) {
        send(articleUpdateKafka,KafkaAction.DELETE);
    }

    private void send(ArticleUpdateKafka articleUpdateKafka, KafkaAction action) {
        KafkaData<ArticleUpdateKafka> kafkaData = KafkaData.of(articleUpdateKafka,action);
        CompletableFuture<SendResult<String, KafkaData>> resultFuture =
                kafkaTemplate.send(TopicConfig.STORY_ARTICLE, kafkaData);
        resultFuture
                .thenAccept(result -> {
                    log.info("Send success: {} Offset: {}",
                            kafkaData, result.getRecordMetadata().offset());
                }).exceptionally(e -> {
                    log.error("Send failed: {}", kafkaData);
                    throw new PublishingFailedException("Publishing failed");
                });
    }

}