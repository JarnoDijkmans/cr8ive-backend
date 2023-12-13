package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPostRepository extends JpaRepository<PostJpaMapper, String> {
    List<PostJpaMapper> findByUserId(long userId);
    Optional<PostJpaMapper> findPostById(long postId);
    void deleteById(long postId);
}
