package com.jarno.cr8ive.domain.factory.impl;

import com.jarno.cr8ive.business.model.request.user.CreateUserRequestModel;
import com.jarno.cr8ive.domain.RoleEnum;
import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.domain.factory.IRoleFactory;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class RoleFactory implements IRoleFactory {
    @Override
    public Set<Roles> createPersonalRole(CreateUserRequestModel requestModel) {
        Roles role = Roles.builder().role(RoleEnum.PERSONAL_ACCOUNT).build();
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(role);
        return userRoles;
    }

    @Override
    public Set<Roles> createBusinessRole(CreateUserRequestModel requestModel) {
        Roles role = Roles.builder().role(RoleEnum.BUSINESS_ACCOUNT).build();
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(role);
        return userRoles;
    }

    @Override
    public Set<Roles> createModeratorRole(CreateUserRequestModel requestModel) {
        Roles role = Roles.builder().role(RoleEnum.MODERATOR).build();
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(role);
        return userRoles;
    }

}
