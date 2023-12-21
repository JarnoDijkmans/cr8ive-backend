package com.jarno.cr8ive.persistance.repository_impl.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

    @Entity
    @Table(name = "hashtag")
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class HashtagJpaMapper {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        @ManyToMany(mappedBy = "hashtags")
        private Set<PostJpaMapper> posts = new HashSet<>();
    }
