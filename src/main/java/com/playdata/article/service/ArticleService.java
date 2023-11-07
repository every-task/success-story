package com.playdata.article.service;

import com.playdata.config.TokenInfo;
import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleKafka;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.request.ArticleUpdateRequest;
import com.playdata.domain.article.response.ArticleAllResponse;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.domain.task.dto.TaskDto;
import com.playdata.exception.NotCorrectMemberException;
import com.playdata.kafka.StoryProducer;
import com.playdata.task.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final StoryProducer storyProducer;
    private final TaskService taskService;

    public void articleWrite(ArticleRequest articleRequest, UUID memberId) {
        Article save = articleRepository.save(articleRequest.toEntityArticle(memberId));
        List<TaskDto> tasks = taskService.taskSaveAll(articleRequest.toEntityTasks(save));
        storyProducer.send(ArticleKafka.of(save,tasks));
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id 못찾음"));
    }

    public ArticleResponse getArticle(Long id) {
        Article articleResponse = findById(id);
        return new ArticleResponse(articleResponse);
    }

    public Page<ArticleAllResponse> getAll(ArticleCondition articleCondition, PageRequest pageRequest) {
        return articleRepository.findAllByCondition(pageRequest, articleCondition);
    }

    public ArticleResponse updateArticle(TokenInfo tokenInfo,
                                         Long articleId,
                                         ArticleUpdateRequest articleUpdateRequest){
        Article article = findById(articleId);
        List<TaskDto> list = article.getTasks().stream().map(TaskDto::new).toList();
        return updateAndSendToKafkaById(tokenInfo, articleUpdateRequest, article, list);
    }
    
    private ArticleResponse updateAndSendToKafkaById(TokenInfo tokenInfo, ArticleUpdateRequest articleUpdateRequest, Article article, List<TaskDto> list) {
        if (tokenInfo.getId().equals(article.getMember().getId())) {
            article.update(articleUpdateRequest.title(), articleUpdateRequest.content(), articleUpdateRequest.category());
            storyProducer.send(ArticleKafka.of(article, list));
            return new ArticleResponse(article);
        }else {
            throw new NotCorrectMemberException("Not Correct Member");
        }
    }
    public void deleteArticle(TokenInfo tokenInfo,
                              Long articleId) {
        Article article = findById(articleId);
        deleteById(tokenInfo, article);
    }

    private static void deleteById(TokenInfo tokenInfo, Article article) {
        if (tokenInfo.getId().equals(article.getMember().getId())) {
            article.delete();
        } else {
            throw new NotCorrectMemberException("Not Correct Member");
        }
    }
}