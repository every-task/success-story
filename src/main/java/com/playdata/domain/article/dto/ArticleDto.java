package com.playdata.domain.article.dto;

import com.playdata.domain.article.entity.Article;
import lombok.Getter;

@Getter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
