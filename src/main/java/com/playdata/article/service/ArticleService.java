package com.playdata.article.service;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleKafka;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.domain.task.dto.TaskDto;
import com.playdata.domain.task.repository.TaskRepository;
import com.playdata.kafka.StoryProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final StoryProducer storyProducer;
    private final TaskRepository taskRepository;

    public void save(ArticleRequest articleRequest, UUID memberId) {
        Article save = articleRepository.save(articleRequest.toEntityArticle(memberId));

        List<TaskDto> tasks = taskRepository.saveAll(articleRequest.toEntityTasks(save))
                .stream()
                .map(TaskDto::new)
                .toList();

        storyProducer.send(ArticleKafka.of(save,tasks));
    }

    public List<ArticleResponse> findAll() {
        List<Article> all = articleRepository.findAll();
        return all.stream().map(ArticleResponse::new).toList();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id 못찾음"));
    }

    public ArticleResponse getArticle(Long id) {
        Optional<Article> articleResponse = articleRepository.findById(id);
        return articleResponse.map(ArticleResponse::new).orElse(null);
    }

}