package com.jarno.cr8ive.persistance.converter;

import com.jarno.cr8ive.persistance.gateways.mapper.UserJpaMapper;
import com.jarno.cr8ive.domain.User;

public class CreateUserConverter {

    public static UserJpaMapper toUserJpaMapper(User user) {
        return UserJpaMapper.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .emailAddress(user.getEmailAddress())
                .birthday(user.getBirthday())
                .profilePicture(user.getProfilePicture())
                .role(user.getRole())
                .location(user.getLocation())
                .passwordHash(user.getPasswordHash())
                .build();


    }
}
