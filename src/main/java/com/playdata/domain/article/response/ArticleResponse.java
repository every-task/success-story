package com.playdata.domain.article.response;

import com.playdata.domain.article.entity.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
