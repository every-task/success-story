package com.playdata.domain.article.kafka;

import com.playdata.domain.article.entity.Article;

public record ArticleKafka(Long id) {
    public static ArticleKafka of(Article article) {
        return new ArticleKafka(article.getId());
    }
}
