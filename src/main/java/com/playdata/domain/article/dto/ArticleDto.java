package com.playdata.domain.article.dto;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private Category category;
    private int view;
    private String thumbnailImageUrl;
    protected LocalDateTime createdAt;


    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.category = article.getCategory();
        this.view = article.getView();
        this.thumbnailImageUrl = article.getThumbnailImageUrl();
        this.createdAt = article.getCreatedAt();
    }
}
