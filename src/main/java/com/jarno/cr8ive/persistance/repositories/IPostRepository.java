package com.jarno.cr8ive.persistance.repositories;

import com.jarno.cr8ive.persistance.gateways.mapper.PostJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<PostJpaMapper, String> {
    List<PostJpaMapper> findByUserId(long userId);
}
