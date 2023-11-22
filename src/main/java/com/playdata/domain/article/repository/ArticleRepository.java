package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, CustomArticleRepository {
    @Query("select a from Article a " +
            "left join fetch a.comments c  " +
            "on c.isDeleted = false "+
            "where a.isDeleted != true and a.id = :id or (c.isDeleted != true or c is null) " +
            " order By a.createdAt desc ")
    Optional<Article> getArticleByIdFetchComment(@Param("id") Long id);
}
