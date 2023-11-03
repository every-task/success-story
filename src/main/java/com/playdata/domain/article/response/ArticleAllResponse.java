package com.playdata.domain.article.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.member.dto.MemberDto;

public class ArticleAllResponse extends ArticleDto {
    private MemberDto member;

    public ArticleAllResponse(Article article) {
        super(article);
        member = new MemberDto(article.getMember());
    }
}
