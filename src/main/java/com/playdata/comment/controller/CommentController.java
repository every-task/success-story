package com.playdata.comment.controller;

import com.playdata.comment.service.CommentService;
import com.playdata.config.TokenInfo;
import com.playdata.domain.comment.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{articleId}")
    public void commentWrite(@AuthenticationPrincipal TokenInfo tokenInfo,@PathVariable(value = "articleId") Long articleId, @RequestBody CommentRequest commentRequest) {
        commentService.commentWrite(commentRequest, articleId,tokenInfo.getId());
    }
}