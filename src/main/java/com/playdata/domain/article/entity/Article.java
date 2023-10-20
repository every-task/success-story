package com.playdata.domain.article.entity;

import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;
    @ManyToOne
    private Member member;

}
