package com.playdata.domain.article.kafka;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import com.playdata.domain.task.dto.TaskDto;

import java.util.List;

public record ArticleKafka(Long id,
                           String title,
                           String content,
                           Category category,
                           List<TaskDto> tasks) {
    public static ArticleKafka create (Article article,
                                  List<TaskDto> tasks) {
        return new ArticleKafka(article.getId(),
                                article.getTitle(),
                                article.getContent(),
                                article.getCategory(),
                                tasks);
    }
}
