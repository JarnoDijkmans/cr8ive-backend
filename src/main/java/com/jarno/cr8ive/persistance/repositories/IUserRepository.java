package com.jarno.cr8ive.persistance.repositories;

import com.jarno.cr8ive.persistance.gateways.mapper.UserJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository <UserJpaMapper, String> {
    @Query("SELECT u FROM UserJpaMapper u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<UserJpaMapper> findUsersByName(@Param("name") String name);
}
