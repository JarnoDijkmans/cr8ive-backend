package com.jarno.cr8ive.persistance.converter.impl;

import com.jarno.cr8ive.persistance.repository_impl.entity.PersonalAccountJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;

public class PersonalAccountConverter {

    private PersonalAccountConverter (){}
    public static PersonalAccountJpaMapper toUserJpaMapper(UserJpaMapper user) {
        return new PersonalAccountJpaMapper(
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
