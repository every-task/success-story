package com.playdata.domain.article.repository;

import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomArticleRepositoryImplTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void findAllByCondition() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        ArticleCondition condition = ArticleCondition.builder().title("개발자가 되고싶어요").build();
        Page<ArticleResponse> allByCondition =
                articleRepository.findAllByCondition(pageRequest, condition);

        assertEquals(allByCondition.getTotalElements(),2);
    }
}