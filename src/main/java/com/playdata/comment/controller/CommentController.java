package com.playdata.comment.controller;

import com.playdata.comment.service.CommentService;
import com.playdata.config.TokenInfo;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.domain.comment.request.CommentUpdateRequest;
import com.playdata.domain.comment.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentResponse> findAllComment() {
        return commentService.getAllComment();
    }

    @PostMapping("articles/{articleId}/comment")
    public void commentWrite(@AuthenticationPrincipal TokenInfo tokenInfo,
                             @PathVariable(value = "articleId") Long articleId,
                             @RequestBody CommentRequest commentRequest) {
        commentService.commentWrite(commentRequest, articleId,tokenInfo.getId());
    }

    @PutMapping("articles/{articleId}/comments/{id}")
    public CommentResponse updateComment(@AuthenticationPrincipal TokenInfo tokenInfo,
                                         @PathVariable(value = "articleId") Long articleId,
                                         @PathVariable(value = "id") Long id,
                                         @RequestBody CommentUpdateRequest commentUpdateRequest) {
        return commentService.updateComment(tokenInfo,id,articleId,commentUpdateRequest);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@AuthenticationPrincipal TokenInfo tokenInfo,
                              @PathVariable(value = "id") Long id) {
        commentService.deleteComment(tokenInfo,id);
    }
}