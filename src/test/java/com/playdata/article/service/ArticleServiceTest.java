package com.playdata.article.service;

import com.playdata.config.TokenInfo;
import com.playdata.domain.article.dto.ArticleCondition;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ArticleServiceTest {
//    @Autowired
//    ArticleRepository articleRepository;
//    @Autowired
//    ArticleService articleService;
//    @Autowired
//    MemberRepository memberRepository;
//    Article save;
//    UUID id = UUID.randomUUID();
//
//    @BeforeEach
//    void init(){
//        Member member = Member.builder().id(id).build();
//        memberRepository.save(member);
//        Article article = Article.builder().member(member).title("test").content("content").category(Category.HEALTH).build();
//        save = articleRepository.save(article);
//    }
//
//    @Test
//    void deleteArticle() {
//        TokenInfo info = TokenInfo.builder().id(id).build();
//        articleService.deleteArticle(info, save.getId());
//        Article article = articleRepository.findById(save.getId()).orElseThrow();
//        assertEquals(save.getId(),article.getId());
//        assertTrue(article.isDeleted());
//        assertEquals(
//                articleRepository.findAllByCondition(
//                        PageRequest.of(0,5),
//                        ArticleCondition.builder().build())
//                        .getTotalElements(),
//                articleRepository.findAll().size()-1);
//    }
}