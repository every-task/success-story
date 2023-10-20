package com.playdata.domain.comment.response;

import com.playdata.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponse {
    private Long id;
    private String content;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
