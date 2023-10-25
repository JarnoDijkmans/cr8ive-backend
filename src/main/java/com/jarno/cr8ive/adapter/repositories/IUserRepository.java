package com.jarno.cr8ive.adapter.repositories;

import com.jarno.cr8ive.adapter.gateways.mapper.UserJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository <UserJpaMapper, String> {
}
