package com.jarno.cr8ive.persistance.repository_impl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seen_posts")
@Getter
@Setter
@NoArgsConstructor
public class SeenPostsJpaMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaMapper user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostJpaMapper post;
}
