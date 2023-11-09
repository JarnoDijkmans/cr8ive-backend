package com.jarno.cr8ive.persistance.converter;

import com.jarno.cr8ive.domain.User;
import com.jarno.cr8ive.persistance.gateways.mapper.UserJpaMapper;

import java.util.Collections;
import java.util.List;

public class GetUsersConverter {
    public static List<User> toUser(List<UserJpaMapper> userJpaMappers){
        if (userJpaMappers == null) {
            return Collections.emptyList();
        }

        return userJpaMappers.stream()
                .map(jpaMapper -> User.builder()
                        .id(jpaMapper.getId())
                        .firstName(jpaMapper.getFirstName())
                        .lastName(jpaMapper.getLastName())
                        .phoneNumber(jpaMapper.getPhoneNumber())
                        .emailAddress(jpaMapper.getEmailAddress())
                        .birthday(jpaMapper.getBirthday())
                        .profilePicture(jpaMapper.getProfilePicture())
                        .role(jpaMapper.getRole())
                        .location(jpaMapper.getLocation())
                        .passwordHash(jpaMapper.getPasswordHash())
                        .bio(jpaMapper.getBio())
                        .currentJob(jpaMapper.getCurrentJob())
                        .followList(jpaMapper.getFollowers().stream()
                                .map(UserJpaMapper::getId)
                                .toList())
                        .followingList(jpaMapper.getFollowing().stream()
                                .map(UserJpaMapper::getId)
                                .toList())
                        .build())
                .toList();
    }
}
