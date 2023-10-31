package com.playdata.domain.article.dto;

import com.playdata.domain.article.entity.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ArticleCondition {
    private String title;
    private String content;
    private Category category;
}
