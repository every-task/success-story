package com.playdata.domain.article.request;
import com.playdata.domain.article.entity.Category;

public record ArticleUpdateRequest(String title, String content, Category category)  {

}
