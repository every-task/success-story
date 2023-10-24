package com.playdata.domain.comment.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.comment.dto.CommentDto;
import com.playdata.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse extends CommentDto {
    private ArticleDto article;

    public CommentResponse(Comment comment) {
        super(comment);
        article = new ArticleDto(comment.getArticle());
    }
}
