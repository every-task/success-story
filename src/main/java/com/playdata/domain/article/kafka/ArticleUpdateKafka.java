package com.playdata.domain.article.kafka;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;

public record ArticleUpdateKafka(Long id, String title, String content, Category category) {
    public static ArticleUpdateKafka of(Article article) {
        return new ArticleUpdateKafka(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCategory());
    }
}
