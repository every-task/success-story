package com.playdata.article.controller;

import com.playdata.article.service.ArticleService;
import com.playdata.config.TokenInfo;
import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.request.ArticleUpdateRequest;
import com.playdata.domain.article.response.ArticleAllResponse;
import com.playdata.domain.article.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void articleWrite(@AuthenticationPrincipal TokenInfo tokenInfo,@RequestBody ArticleRequest articleRequest) {
        articleService.articleWrite(articleRequest,tokenInfo.getId());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleResponse getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping
    public Page<ArticleAllResponse> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
            ArticleCondition articleCondition
    ) {
        return articleService.getAll(articleCondition, PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleResponse updateArticle(@AuthenticationPrincipal TokenInfo tokenInfo,
                                         @PathVariable("id")Long id,
                                         @RequestBody ArticleUpdateRequest articleUpdateRequest) {
        return articleService.updateArticle(tokenInfo, id, articleUpdateRequest);
    }
}
