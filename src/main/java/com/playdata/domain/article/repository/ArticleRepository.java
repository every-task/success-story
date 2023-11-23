package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.response.ArticleAllResponse;
import com.playdata.domain.article.response.ArticleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, CustomArticleRepository {
    @Query("select a from Article a where a.isDeleted = false order by a.view desc, a.createdAt desc limit :pageSize")
    List<ArticleAllResponse> findAllByViewAndCreatedAt(int pageSize);

}
