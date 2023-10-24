package com.playdata.article.controller;

import com.playdata.article.service.ArticleService;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/success")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public void save(@RequestBody ArticleRequest articleRequest) {
        articleService.save(articleRequest);
    }

    @GetMapping
    public List<ArticleResponse> getAll() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }
}
