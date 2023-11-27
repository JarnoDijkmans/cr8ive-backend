package com.jarno.cr8ive.persistance.repository_jpa;

import com.jarno.cr8ive.persistance.repository_impl.entity.PersonalAccountJpaMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPersonalAccountRepository extends JpaRepository<PersonalAccountJpaMapper, Long> {

}
