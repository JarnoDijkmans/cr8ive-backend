package com.jarno.cr8ive.adapter.repositories;

import com.jarno.cr8ive.adapter.gateways.mapper.HashtagJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHashtagRepository extends JpaRepository<HashtagJpaMapper, Integer> {

}
