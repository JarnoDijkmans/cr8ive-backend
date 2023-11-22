package com.jarno.cr8ive.persistance.converter;

import com.jarno.cr8ive.domain.user.IUser;
import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;

import java.util.List;

public interface UserConverter {
    IUser toUser(UserJpaMapper jpaMapper);

    List<IUser> toUserList(List<UserJpaMapper> userJpaMappers);

    UserJpaMapper toUserJpaMapper(IUser user);
}
