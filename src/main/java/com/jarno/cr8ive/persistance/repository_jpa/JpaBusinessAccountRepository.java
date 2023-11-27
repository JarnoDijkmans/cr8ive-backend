package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.BusinessAccountJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBusinessAccountRepository extends JpaRepository<BusinessAccountJpaMapper, Long> {
}
