package com.playdata.domain.article.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.dto.CommentDto;
import com.playdata.domain.member.dto.MemberDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleResponse extends ArticleDto {
    private MemberDto member;

    public ArticleResponse(Article article) {
        super(article);

        member = new MemberDto(article.getMember());
    }
}
