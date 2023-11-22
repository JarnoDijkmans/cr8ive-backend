package com.jarno.cr8ive.persistance.converter;

import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.persistance.repository_impl.entity.RolesJpaMapper;

import java.util.Set;

public interface RolesConverter {
    Set<RolesJpaMapper> toRolesJpa (Set<Roles> roles);

    Set<Roles> toRoles(Set<RolesJpaMapper> jpaMappers);
}
