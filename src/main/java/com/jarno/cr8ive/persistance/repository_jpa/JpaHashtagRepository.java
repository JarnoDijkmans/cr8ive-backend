package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.HashtagJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHashtagRepository extends JpaRepository<HashtagJpaMapper, Integer> {
    @Query("DELETE FROM HashtagJpaMapper ph WHERE ph.posts = :postId")
    void deletePostItsHashtags(@Param("postId") long postId);
}
