package com.playdata.domain.article.repository;

import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.response.ArticleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CustomArticleRepository {

    Page<ArticleResponse> findAllByCondition(
            PageRequest request,
            ArticleCondition condition
    );
}