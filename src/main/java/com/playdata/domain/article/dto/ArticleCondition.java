package com.playdata.domain.article.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ArticleCondition {
    private String title;
    private String content;

}
