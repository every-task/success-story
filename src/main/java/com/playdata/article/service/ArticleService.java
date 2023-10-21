package com.playdata.article.service;

import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void save(ArticleRequest articleRequest) {
        articleRepository.save(articleRequest.toEntity());
    }
}