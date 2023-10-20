package com.playdata.domain.article.request;

import com.playdata.domain.article.entity.Article;
import lombok.Builder;
import lombok.Data;


@Data
public class ArticleRequest {
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
