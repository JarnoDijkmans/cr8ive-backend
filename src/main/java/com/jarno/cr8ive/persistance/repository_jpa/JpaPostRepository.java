package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPostRepository extends JpaRepository<PostJpaMapper, Long> {
    List<PostJpaMapper> findByUserId(long userId);
    Optional<PostJpaMapper> findPostById(long postId);
    @Modifying
    @Query("DELETE FROM PostJpaMapper p WHERE p.id = :postId")
    void deletePostById(@Param("postId") Long postId);

    @Query("SELECT p FROM PostJpaMapper p ORDER BY p.creationDate DESC")
    List<PostJpaMapper> findLatestPosts(Pageable pageable);

    @Query("Select size(p.likes) FROM PostJpaMapper p WHERE p.id = :postId")
    long findLikeCountByPostId (@Param("postId") long postId);

    @Query("SELECT p FROM PostJpaMapper p " +
            "WHERE p.creationDate >= :startDate " +
            "AND p.creationDate <= :endDate " +
            "ORDER BY SIZE(p.likes) DESC")
    List<PostJpaMapper> findPostsInDateRange(Date startDate, Date endDate, Pageable pageable);

    @Query("SELECT p FROM PostJpaMapper p " +
            "JOIN p.hashtags h " +
            "WHERE h.id = :hashtagId " +
            "ORDER BY p.creationDate DESC")
    List<PostJpaMapper> findPostsByHashtagId(@Param("hashtagId") int hashtagId, Pageable pageable);
}


