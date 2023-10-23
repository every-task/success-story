package com.playdata.article.service;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleKafka;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.kafka.SuccessProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final SuccessProducer successProducer;

    public void save(ArticleRequest articleRequest) {
        Article save = articleRepository.save(articleRequest.toEntity());
        successProducer.send(ArticleKafka.of(save));
    }
}