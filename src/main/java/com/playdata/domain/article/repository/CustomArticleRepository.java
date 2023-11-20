package com.playdata.domain.article.repository;

import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.response.ArticleAllResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CustomArticleRepository {

    Page<ArticleAllResponse> findAllByCondition(
            PageRequest request,
            ArticleCondition condition
    );
}
