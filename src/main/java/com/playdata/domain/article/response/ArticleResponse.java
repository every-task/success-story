package com.playdata.domain.article.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.response.CommentResponse;
import com.playdata.domain.member.dto.MemberDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleResponse extends ArticleDto {
    private List<CommentResponse> comments;
    private MemberDto member;

    public ArticleResponse(Article article) {
        super(article);
        comments = article.getComments()
                .stream()
                .map(CommentResponse::new)
                .toList();

        member = new MemberDto(article.getMember());
    }
}
