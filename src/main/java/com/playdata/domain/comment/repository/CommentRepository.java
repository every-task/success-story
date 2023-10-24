package com.playdata.domain.comment.repository;

import com.playdata.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByArticleId(Long ArticleId);
}
