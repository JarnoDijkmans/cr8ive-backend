package com.jarno.cr8ive.adapter.gateways.mapper;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "post_content")
public class ContentJpaMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "file_url")
    private String fileUrl;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostJpaMapper post;
}