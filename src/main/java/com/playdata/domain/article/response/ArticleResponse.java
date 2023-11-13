package com.playdata.domain.article.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.dto.CommentDto;
import com.playdata.domain.member.dto.MemberDto;
import com.playdata.domain.task.dto.TaskDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleResponse extends ArticleDto {
    private List<CommentDto> comments;
    private MemberDto member;

    public ArticleResponse(Article article) {
        super(article);
        comments = article.getComments()
                .stream()
                .map(CommentDto::new)
                .toList();

        member = new MemberDto(article.getMember());
    }
}
