package com.playdata.article.service;

import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleKafka;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.domain.task.dto.TaskDto;
import com.playdata.domain.task.repository.TaskRepository;
import com.playdata.kafka.StoryProducer;
import com.playdata.task.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final TaskService taskService;

    public void save(ArticleRequest articleRequest, UUID memberId) {
        Article save = articleRepository.save(articleRequest.toEntityArticle(memberId));
        List<TaskDto> tasks = taskService.saveAll(articleRequest.toEntityTasks(save));
        storyProducer.send(ArticleKafka.of(save,tasks));
    }


    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id 못찾음"));
    }

    public ArticleResponse getArticle(Long id) {
        Optional<Article> articleResponse = articleRepository.findById(id);
        return articleResponse.map(ArticleResponse::new).orElse(null);
    }

    public Page<ArticleResponse> getAll(ArticleCondition articleCondition,PageRequest pageRequest) {
        return articleRepository.findAllByCondition(pageRequest, articleCondition);
    }
}