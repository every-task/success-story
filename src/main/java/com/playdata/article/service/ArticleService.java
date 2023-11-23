package com.playdata.article.service;

import com.playdata.config.TokenInfo;
import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleUpdateKafka;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.request.ArticleUpdateRequest;
import com.playdata.domain.article.response.ArticleAllResponse;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.domain.task.dto.TaskDto;
import com.playdata.exception.NotCorrectMemberException;
import com.playdata.kafka.ArticleProducer;
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
    private final ArticleProducer articleProducer;
    private final TaskService taskService;

    public void articleWrite(ArticleRequest articleRequest, UUID memberId) {
        Article save = articleRepository.save(articleRequest.toEntityArticle(memberId));
        List<TaskDto> tasks = taskService.taskSaveAll(articleRequest.toEntityTasks(save));
        storyProducer.sendCreate(save,tasks);
    }

    public Article findById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("No search id.id={%s}", id)));
    }

    public ArticleResponse getArticle(Long id) {
        Article articleResponse = articleRepository.getArticleByIdFetchComment(id);
        int view = articleResponse.getView()+1;
        articleResponse.updateView(view);
        return new ArticleResponse(articleResponse);
    }

    public Page<ArticleAllResponse> getAll(ArticleCondition articleCondition, PageRequest pageRequest) {
        return articleRepository.findAllByCondition(pageRequest, articleCondition);
    }

    public ArticleResponse updateArticle(TokenInfo tokenInfo,
                                         Long articleId,
                                         ArticleUpdateRequest articleUpdateRequest){
        Article article = findById(articleId);
        return updateAndSendToKafkaById(tokenInfo, articleUpdateRequest, article);
    }
    
    private ArticleResponse updateAndSendToKafkaById(TokenInfo tokenInfo,
                                                     ArticleUpdateRequest articleUpdateRequest,
                                                     Article article) {
        if (isRequestAuthorized(tokenInfo, article)) {
            article.update(articleUpdateRequest.title(), articleUpdateRequest.content(), articleUpdateRequest.category());
            ArticleUpdateKafka articleUpdateKafka = ArticleUpdateKafka.create(article);
            articleProducer.sendUpdate(articleUpdateKafka);
            return new ArticleResponse(article);
        }else {
            throw new NotCorrectMemberException("Not Correct Member, memberId = {%s}"
                    .formatted(article.getMember().getId()));
        }
    }

    public void deleteArticle(TokenInfo tokenInfo,
                              Long articleId) {
        Article article = findById(articleId);
        deleteById(tokenInfo, article);
        ArticleUpdateKafka articleUpdateKafka = ArticleUpdateKafka.create(article);
        articleProducer.sendDelete(articleUpdateKafka);
    }

    private void deleteById(TokenInfo tokenInfo, Article article) {
        if (isRequestAuthorized(tokenInfo, article)) {
            article.delete();
        } else {
            throw new NotCorrectMemberException("Not Correct MemberId, memberId = {%s}"
                    .formatted(article.getMember().getId()));
        }
    }

    private boolean isRequestAuthorized(TokenInfo tokenInfo, Article article) {
        return tokenInfo.getId().equals(article.getMember().getId());
    }

    public List<ArticleResponse> viewDesc() {
        return articleRepository.findAllByViewAndCreatedAt(9);
    }

}