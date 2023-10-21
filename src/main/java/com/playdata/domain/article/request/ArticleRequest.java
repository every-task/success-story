package com.playdata.domain.article.request;

import com.playdata.domain.article.entity.Article;

public record ArticleRequest(String title,String content) {

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}