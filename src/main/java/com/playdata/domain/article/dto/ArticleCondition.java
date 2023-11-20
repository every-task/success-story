package com.playdata.domain.article.dto;

import com.playdata.domain.article.entity.Category;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ArticleCondition {
    private String title;
    private String content;
    private List<Category> categories;
    private Boolean createAtAsc;
}
