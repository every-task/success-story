package com.playdata.domain.article.repository;

import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import com.playdata.domain.article.entity.QArticle;
import com.playdata.domain.article.response.ArticleAllResponse;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.entity.QComment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static com.playdata.domain.article.entity.QArticle.article;

public class CustomArticleRepositoryImpl implements CustomArticleRepository {
    private final JPAQueryFactory queryFactory;

    public CustomArticleRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ArticleAllResponse> findAllByCondition(PageRequest request, ArticleCondition condition) {

        JPAQuery<Article> articles = queryFactory
                .select(article)
                .from(article)
                .join(article.member)
                .leftJoin(article.comments)
                .fetchJoin()
                .where(
                        contentContains(condition.getContent()),
                        categoryListEq(condition.getCategories()),
                        article.isDeleted.eq(true).not(),
                        titleContains(condition.getTitle())
                )
                .orderBy(createAtOrder(condition.getCreateAtAsc()))
                .offset(request.getOffset())
                .limit(request.getPageSize());
        List<Article> articleList = articles.fetch(); //fetch로 리스트 반환

        Long totalSize = queryFactory
                .select(article.count())
                .from(article)
                .where(
                        contentContains(condition.getContent()),
                        categoryListEq(condition.getCategories()),
                        article.isDeleted.eq(true).not(), //true 인것만 안보임
                        titleContains(condition.getTitle())
                )
                .fetchOne(); //단건조회
        PageImpl<Article> articlesList = new PageImpl<>(articleList, request, totalSize);
        Page<ArticleAllResponse> map = articlesList.map(ArticleAllResponse::new);
        return map;

    }

    private BooleanExpression contentContains(String content) {
        return content == null || content.isEmpty()
                ? null
                : article.content.contains(content);
    }

    private BooleanExpression titleContains(String title) {
        return title == null || title.isEmpty()
                ? null
                : article.title.contains(title);
    }

    private BooleanBuilder categoryListEq(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return null;
        }
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        categories.forEach(t ->booleanBuilder.or(article.category.eq(t)));
        return booleanBuilder;
    }
    private OrderSpecifier<LocalDateTime> createAtOrder(Boolean isAsc){
        return isAsc != null&&isAsc? article.createdAt.asc() : article.createdAt.desc();
    }
}
