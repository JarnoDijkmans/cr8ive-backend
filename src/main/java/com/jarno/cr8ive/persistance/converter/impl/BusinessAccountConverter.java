package com.jarno.cr8ive.persistance.converter.impl;

import com.jarno.cr8ive.persistance.repository_impl.entity.BusinessAccountJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;

public class BusinessAccountConverter {
    public static BusinessAccountJpaMapper toUserJpaMapper (UserJpaMapper user){
        return new BusinessAccountJpaMapper(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmailAddress(),
                user.getBirthday(),
                user.getProfilePicture(),
                user.getUserRoles(),
                user.getPasswordHash()
        );
    }
}
