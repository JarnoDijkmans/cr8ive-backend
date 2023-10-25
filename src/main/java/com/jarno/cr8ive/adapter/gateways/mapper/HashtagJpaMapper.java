package com.jarno.cr8ive.adapter.gateways.mapper;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hashtag")
@Getter
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
