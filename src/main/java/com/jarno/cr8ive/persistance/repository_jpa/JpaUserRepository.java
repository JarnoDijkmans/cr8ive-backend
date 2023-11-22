package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserRepository extends JpaRepository <UserJpaMapper, String> {
    @Query("SELECT u FROM UserJpaMapper u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<UserJpaMapper> findUsersByName(@Param("name") String name);

    UserJpaMapper findUserByEmailAddress (String emailAddress);

    UserJpaMapper findUserById(long id);
}
