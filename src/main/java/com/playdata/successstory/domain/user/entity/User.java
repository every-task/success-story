package com.playdata.successstory.domain.user.entity;

import com.playdata.successstory.domain.article.entity.Article;
import com.playdata.successstory.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private UUID id;

    private String nickname;
    private String profileImageUrl;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

}
