package com.jarno.cr8ive.adapter.gateways.mapper;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "Content")
public class ContentJpaMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fileUrl;
}