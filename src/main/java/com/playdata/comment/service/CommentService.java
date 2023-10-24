package com.playdata.comment.service;

import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.domain.member.entity.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public void save(CommentRequest commentRequest, Long articleId, UUID memberId) {
        commentRepository.save(commentRequest.toEntity(memberId, articleId));
    }
}
