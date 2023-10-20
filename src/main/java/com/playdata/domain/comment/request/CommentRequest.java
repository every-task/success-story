package com.playdata.domain.comment.request;

import com.playdata.domain.comment.entity.Comment;
import lombok.Data;

@Data
public class CommentRequest {
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
