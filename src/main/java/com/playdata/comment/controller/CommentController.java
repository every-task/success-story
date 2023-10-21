package com.playdata.comment.controller;

import com.playdata.comment.service.CommentService;
import com.playdata.domain.comment.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/success")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public void save(@RequestBody CommentRequest commentRequest) {
        commentService.save(commentRequest);
    }
}