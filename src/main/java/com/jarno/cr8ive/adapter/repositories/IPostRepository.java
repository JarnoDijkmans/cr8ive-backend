package com.jarno.cr8ive.adapter.repositories;

import com.jarno.cr8ive.adapter.gateways.mapper.PostJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<PostJpaMapper, String> {
}
