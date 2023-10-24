package com.playdata.domain.member.entity;

import com.playdata.domain.article.entity.Article;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    private UUID id;
    private String nickname;
    private String profileImageUrl;

    @OneToMany(mappedBy = "member")
    private List<Article> articles;

    @Builder
    public Member(UUID id, String nickname, String profileImageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
