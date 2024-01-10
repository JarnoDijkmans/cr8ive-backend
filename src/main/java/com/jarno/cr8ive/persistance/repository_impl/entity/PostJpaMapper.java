package com.jarno.cr8ive.persistance.repository_impl.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Post")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostJpaMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentJpaMapper> postContents;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LikeJpaMapper> likes = new HashSet<>();
    private long shareCount;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "post_hashtags",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "hashtag_id") }
    )
    private Set<HashtagJpaMapper> hashtags = new HashSet<>();
    private long userId;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<SeenPostsJpaMapper> seenByUsers = new HashSet<>();
}
