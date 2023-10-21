package com.playdata.domain.comment.request;

import com.playdata.domain.comment.entity.Comment;

public record CommentRequest(String content) {

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
