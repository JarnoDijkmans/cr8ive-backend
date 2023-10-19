package com.jarno.cr8ive.adapter.gateways.mapper;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    @OneToMany
    @JoinTable(name = "Post_Content",
                joinColumns = @JoinColumn(name= "post_id"))
    private List<ContentJpaMapper> content;
    private String description;
    private Date creationDate;
    private long likes;
    private long shareCount;
    private int commandId;
    @ManyToMany
    @JoinTable(name = "Post_Hashtags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    private List<HashtagJpaMapper> hashtags;
    private long user;
}
