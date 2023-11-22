package com.playdata.article.controller;

import com.playdata.article.service.ArticleService;
import com.playdata.config.TokenInfo;
import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.entity.Category;
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

import java.util.List;

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
    public ArticleResponse getArticle(@PathVariable("id") Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping
    public Page<ArticleAllResponse> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "9") Integer size,
            @RequestParam(value = "category", required = false) List<String> categorise,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "createAtAsc", required = false) Boolean createAtAsc
    ) {
        List<Category> categoryList = categorise!=null?categorise.stream().map(Category::valueOf).toList():null;
        ArticleCondition articleCondition = ArticleCondition
                .builder()
                .title(title)
                .content(content)
                .categories(categoryList)
                .createAtAsc(createAtAsc)
                .build();
        return articleService.getAll(articleCondition, PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleResponse updateArticle(@AuthenticationPrincipal TokenInfo tokenInfo,
                                         @PathVariable("id")Long id,
                                         @RequestBody ArticleUpdateRequest articleUpdateRequest) {
        return articleService.updateArticle(tokenInfo, id, articleUpdateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@AuthenticationPrincipal TokenInfo tokenInfo,
                              @PathVariable("id")Long id) {
        articleService.deleteArticle(tokenInfo,id);
    }

    @GetMapping("/popular")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleResponse> popularView() {
        return articleService.viewDesc();
    }
}
