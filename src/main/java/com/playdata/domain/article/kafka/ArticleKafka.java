package com.playdata.domain.article.kafka;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.task.dto.TaskDto;

import java.util.List;

public record ArticleKafka(Long id, String title, String content, List<TaskDto> tasks) {
    public static ArticleKafka of(Article article, List<TaskDto> tasks) {
        return new ArticleKafka(article.getId(), article.getTitle(), article.getContent(), tasks);
    }
}
