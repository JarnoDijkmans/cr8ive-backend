package com.jarno.cr8ive.adapter.repositories;

import com.jarno.cr8ive.adapter.gateways.mapper.ContentJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContentRepository extends JpaRepository<ContentJpaMapper, String> {
}
