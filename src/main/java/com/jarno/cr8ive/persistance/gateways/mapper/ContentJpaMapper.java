package com.jarno.cr8ive.persistance.gateways.mapper;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "post_content")
public class ContentJpaMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "file_url")
    private String fileUrl;
    @Column (name = "extension")
    private String extension;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostJpaMapper post;
}