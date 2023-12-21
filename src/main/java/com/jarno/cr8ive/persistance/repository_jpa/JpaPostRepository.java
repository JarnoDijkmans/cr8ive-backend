package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPostRepository extends JpaRepository<PostJpaMapper, String> {
    List<PostJpaMapper> findByUserId(long userId);
    Optional<PostJpaMapper> findPostById(long postId);
    @Modifying
    @Query("DELETE FROM PostJpaMapper p WHERE p.id = :postId")
    void deletePostById(@Param("postId") Long postId);
}
