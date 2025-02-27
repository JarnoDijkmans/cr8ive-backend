package com.jarno.cr8ive.persistance.repository_impl.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Likes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeJpaMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostJpaMapper post;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;
}