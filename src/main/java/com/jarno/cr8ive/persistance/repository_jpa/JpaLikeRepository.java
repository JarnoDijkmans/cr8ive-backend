package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.LikeJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaLikeRepository extends JpaRepository<LikeJpaMapper, Long> {
     Optional<LikeJpaMapper> findByPostIdAndUserId(long postId, long userId);

     boolean existsByPostIdAndUserId(Long postId, Long userId);
}
