package com.jarno.cr8ive.persistance.repositories;

import com.jarno.cr8ive.persistance.gateways.mapper.ContentJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContentRepository extends JpaRepository<ContentJpaMapper, String> {
}
