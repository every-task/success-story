package com.playdata.domain.comment.response;

import com.playdata.domain.comment.dto.CommentDto;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class CommentResponse extends CommentDto {
    private MemberDto member;

    public CommentResponse(Comment comment) {
        super(comment);
        member = new MemberDto(comment.getMember());
    }
}
