package com.jarno.cr8ive.adapter.repositories;

import com.jarno.cr8ive.adapter.gateways.mapper.PostJpaMapper;
import com.jarno.cr8ive.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<PostJpaMapper, String> {
    List<PostJpaMapper> findByUserId(long userId);
}
