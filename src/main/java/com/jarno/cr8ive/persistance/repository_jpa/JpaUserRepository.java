package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserRepository extends JpaRepository <UserJpaMapper, Long> {
    @Query("SELECT DISTINCT u FROM UserJpaMapper u JOIN u.userRoles r " +
            "WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND r.role <> 'MAINTAINER'")
    List<UserJpaMapper> findUsersByNameExcludingMaintainers(@Param("name") String name);

    UserJpaMapper findUserByEmailAddress (String emailAddress);

    UserJpaMapper findUserById(long id);
    boolean existsByEmailAddress (String emailAddress);
}
