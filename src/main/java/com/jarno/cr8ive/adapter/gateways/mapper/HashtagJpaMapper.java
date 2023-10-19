package com.jarno.cr8ive.adapter.gateways.mapper;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Hashtag")
public class HashtagJpaMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
