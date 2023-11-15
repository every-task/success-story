package com.playdata.comment.service;

import com.playdata.config.TokenInfo;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.domain.comment.request.CommentUpdateRequest;
import com.playdata.domain.comment.response.CommentResponse;
import com.playdata.exception.InvalidArticleException;
import com.playdata.exception.NotCorrectMemberException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    public void commentWrite(CommentRequest commentRequest, Long articleId, UUID memberId) {
        commentRepository.save(commentRequest.toEntity(memberId, articleId));
    }

    public CommentResponse updateComment(TokenInfo tokenInfo,
                                         Long id,
                                         Long articleId,
                                         CommentUpdateRequest commentUpdateRequest) {
        Comment comment = findById(id);
        return updateById(tokenInfo, comment, articleId, commentUpdateRequest);
    }
    
    public void deleteComment(TokenInfo tokenInfo, Long id, Long articleId) {
        Comment comment = findById(id);
        deleteById(tokenInfo, comment, articleId);
    }

    private CommentResponse updateById(TokenInfo tokenInfo,
                                       Comment comment,
                                       Long articleId,
                                       CommentUpdateRequest commentUpdateRequest) {
        if (!tokenInfo.getId().equals(comment.getMember().getId())) {
            throw new NotCorrectMemberException("Not Correct Member, memberId = {%s}"
                    .formatted(comment.getMember().getId()));
        }
        if(!comment.getArticle().getId().equals(articleId)){
            throw new InvalidArticleException("Not Invalid Article, ArticleId = {%s}"
                    .formatted(comment.getArticle().getId()));
        }
        comment.update(commentUpdateRequest.content());
        return new CommentResponse(comment);
    }

    private void deleteById(TokenInfo tokenInfo, Comment comment, Long articleId) {
        if (!tokenInfo.getId().equals(comment.getMember().getId())) {
            throw new NotCorrectMemberException("Not Correct Member, memberId = {%s}"
                    .formatted(comment.getMember().getId()));
        }
        if(!comment.getArticle().getId().equals(articleId)){
            throw new InvalidArticleException("Not Invalid Article, ArticleId = {%s}"
                    .formatted(comment.getArticle().getId()));
        }
        comment.delete();
    }
    private Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No search id"));
    }
}
