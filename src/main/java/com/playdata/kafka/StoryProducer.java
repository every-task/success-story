package com.playdata.kafka;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleKafka;
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
    private final KafkaTemplate<String, ArticleKafka> kafkaTemplate;

    @Async
    public void sendCreate(Article article, List<TaskDto> tasks) {
        send(article, tasks, "create");
    }

    @Async
    public void sendUpdate(Article article, List<TaskDto> tasks) {
        send(article, tasks, "update");
    }

    @Async
    public void sendDelete(Article article, List<TaskDto> tasks) {
        send(article, tasks, "delete");
    }

    private void send(Article article, List<TaskDto> tasks, String action) {
        ArticleKafka articleKafka = ArticleKafka.of(article, tasks, action);
        CompletableFuture<SendResult<String, ArticleKafka>> resultFuture =
                kafkaTemplate.send(TopicConfig.STORY, articleKafka);
        resultFuture
                .thenAccept(result -> {
                    log.info("Send success: {} Offset: {}",
                            articleKafka, result.getRecordMetadata().offset());
                }).exceptionally(e -> {
                            log.error("Send failed: {}", articleKafka);
                            throw new PublishingFailedException("Publishing failed");
                        });
    }
}
