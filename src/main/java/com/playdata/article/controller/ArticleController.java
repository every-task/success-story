package com.playdata.article.controller;

import com.playdata.article.service.ArticleService;
import com.playdata.domain.article.request.ArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/success")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public void save(@RequestBody ArticleRequest articleRequest) {
        articleService.save(articleRequest);
    }
}
