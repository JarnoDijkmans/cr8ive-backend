package com.jarno.cr8ive.domain.factory;

import com.jarno.cr8ive.business.model.request.user.CreateUserRequestModel;
import com.jarno.cr8ive.domain.Roles;

import java.util.Set;


public interface IRoleFactory {
    Set<Roles> createPersonalRole(CreateUserRequestModel requestModel);
    Set<Roles> createBusinessRole(CreateUserRequestModel requestModel);
    Set<Roles> createModeratorRole(CreateUserRequestModel requestModel);

}
