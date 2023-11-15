package com.playdata.kafka;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleKafka;
import com.playdata.domain.article.kafka.KafkaAction;
import com.playdata.domain.article.kafka.KafkaData;
import com.playdata.domain.task.dto.TaskDto;
import com.playdata.exception.PublishingFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class StoryProducer {
    private final KafkaTemplate<String, KafkaData> kafkaTemplate;

    @Async
    public void sendCreate(Article article, List<TaskDto> tasks) {
        send(article, tasks, KafkaAction.CREATE);
    }

    private void send(Article article, List<TaskDto> tasks, KafkaAction action) {
        ArticleKafka articleKafka = ArticleKafka.create(article, tasks);
        KafkaData kafkaData = KafkaData.of(articleKafka, action);
        CompletableFuture<SendResult<String, KafkaData>> resultFuture =
                kafkaTemplate.send(TopicConfig.STORY, kafkaData );
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
