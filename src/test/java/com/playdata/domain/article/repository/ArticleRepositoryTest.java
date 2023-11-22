package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository repository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CommentRepository commentRepository;
    Article save;
    @BeforeEach
    void init() {
        List<Comment> list = new ArrayList<>();
        Member member = Member.builder()
                .id(UUID.randomUUID())
                .nickname("te")
                .build();
        Member save1 = memberRepository.saveAndFlush(member);
//        memberRepository.flush();
        Article article = Article.builder()
            .title("test")
            .content("content")
            .category(Category.HEALTH)
            .member(save1)
//            .comments(list)
            .build();
        save = repository.save(article);
        for (int i = 0; i < 10; i++) {

            Comment comment = Comment.
                    builder()
                    .content("content" + i)
                    .member(save1)
                    .article(save)
                    .build();
            if(i%3==0) comment.delete();
            list.add(comment);

        }
        commentRepository.saveAll(list);

    }

    @Test
    void getArticleByIdFetchComment(){
        Article articleByIdFetchComment = repository.getArticleByIdFetchComment(save.getId());
        Article article = articleByIdFetchComment;
        System.out.println(article.isDeleted());
        Assertions.assertEquals(article.getComments().size(),6);

    }


}