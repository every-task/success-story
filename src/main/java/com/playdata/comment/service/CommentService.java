package com.playdata.comment.service;

import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public void save(CommentRequest commentRequest) {
        commentRepository.save(commentRequest.toEntity());
    }
}
