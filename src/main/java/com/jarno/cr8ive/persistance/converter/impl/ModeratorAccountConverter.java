package com.jarno.cr8ive.persistance.converter.impl;

import com.jarno.cr8ive.persistance.repository_impl.entity.ModeratorAccountJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;

public class ModeratorAccountConverter {
    private ModeratorAccountConverter (){}
    public static ModeratorAccountJpaMapper toUserJpaMapper(UserJpaMapper user) {
        return new ModeratorAccountJpaMapper(
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
