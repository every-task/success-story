package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.response.ArticleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, CustomArticleRepository {
    @Query("select a from Article a where a.isDeleted = false order by a.view desc, a.createdAt desc limit :pageSize")
    List<ArticleResponse> findAllByViewAndCreatedAt(int pageSize);

    @Query("select a from Article a where a.isDeleted = false and a.id =:id order by a.createdAt desc ")
    Optional<Article> findArticleById(@Param("id")Long id);
}
