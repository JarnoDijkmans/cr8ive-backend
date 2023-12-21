package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.MessageJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaChatRepository extends JpaRepository<MessageJpaMapper, Long> {

    @Query("SELECT m FROM MessageJpaMapper m WHERE (m.senderId = :user1 AND m.receiverId = :user2) OR (m.senderId = :user2 AND m.receiverId = :user1)")
    List<MessageJpaMapper> findMessagesBetweenUsers(@Param("user1") Long user1, @Param("user2") Long user2);
}
