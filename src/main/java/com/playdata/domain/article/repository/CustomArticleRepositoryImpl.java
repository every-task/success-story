package com.playdata.domain.article.repository;

import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.response.ArticleResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.playdata.domain.article.entity.QArticle.article;

public class CustomArticleRepositoryImpl implements CustomArticleRepository {
    private final JPAQueryFactory queryFactory;

    public CustomArticleRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ArticleResponse> findAllByCondition(PageRequest request, ArticleCondition condition) {
        JPAQuery<Article> articles = queryFactory
                .select(article)
                .from(article)
                .leftJoin(article.comments)
                .fetchJoin()
                .where(
                        contentContains(condition.getContent()),
                        titleEq(condition.getTitle())
                )
                .orderBy(article.createdAt.desc(), article.createdAt.asc())
                .offset(request.getPageNumber())
                .limit(request.getPageSize());
        List<Article> articleList = articles.fetch(); //fetch로 리스트 반환

        Long totalSize = queryFactory
                .select(article.count())
                .from(article)
                .where(
                        contentContains(condition.getContent()),
                        titleEq(condition.getTitle())
                )
                .fetchOne(); //단건조회
        PageImpl<Article> articlesList = new PageImpl<>(articleList, request, totalSize);
        Page<ArticleResponse> map = articlesList.map(ArticleResponse::new);

        return map;

    }


    private BooleanExpression contentContains(String content) {
        return content == null || content.isEmpty()
                ? null
                : article.content.contains(content);
    }
    private BooleanExpression titleEq(String title) {
        return title == null
                ? null
                : article.title.eq(title);
    }

}