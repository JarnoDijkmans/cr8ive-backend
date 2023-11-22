package com.jarno.cr8ive.persistance.converter.impl;

import com.jarno.cr8ive.domain.RoleEnum;
import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.persistance.converter.RolesConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.RolesJpaMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class RolesConverterImpl implements RolesConverter {
    @Override
    public Set<RolesJpaMapper> toRolesJpa (Set<Roles> roles){
        Set<RolesJpaMapper> rolesJpaMappers = new HashSet<>();
        if (roles != null) {
            for (Roles role : roles){
                rolesJpaMappers.add(createJpaRoles(role.getRole()));
            }
        }
        return rolesJpaMappers;
    }
    @Override
    public Set<Roles> toRoles(Set<RolesJpaMapper> jpaMappers){
        Set<Roles> roles = new HashSet<>();
        if (jpaMappers != null) {
            for (RolesJpaMapper jpaMapper : jpaMappers){
                roles.add(createRole(jpaMapper.getRole()));
            }
        }
        return roles;
    }

    private RolesJpaMapper createJpaRoles(RoleEnum roleEnum){
        return RolesJpaMapper.builder()
                .role(roleEnum)
                .build();
    }

    private Roles createRole(RoleEnum roleEnum){
        return Roles.builder()
                .role(roleEnum)
                .build();
    }
}
