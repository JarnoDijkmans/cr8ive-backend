package com.jarno.cr8ive.persistance.converter.impl;

import com.jarno.cr8ive.domain.factory.impl.UserFactory;
import com.jarno.cr8ive.domain.user.IUser;
import com.jarno.cr8ive.domain.user.impl.BusinessAccount;
import com.jarno.cr8ive.domain.user.impl.PersonalAccount;
import com.jarno.cr8ive.persistance.converter.RolesConverter;
import com.jarno.cr8ive.persistance.converter.UserConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.BusinessAccountJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.PersonalAccountJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.RolesJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserConverterImpl implements UserConverter {

    private final UserFactory factory;
    private final RolesConverter roleConverter;

    @Autowired
    private UserConverterImpl(UserFactory factory, RolesConverter converter){
        this.factory = factory;
        this.roleConverter = converter;
    }
    @Override
    public List<IUser> toUserList(List<UserJpaMapper> userJpaMappers) {
        if (userJpaMappers == null) {
            return Collections.emptyList();
        }

        return userJpaMappers.stream()
                .map(this::mapToUser)
                .toList();
    }

    @Override
    public IUser toUser(UserJpaMapper jpaMapper) {
        return mapToUser(jpaMapper);
    }

    private IUser mapToUser(UserJpaMapper jpaMapper) {
        IUser user;
        if (jpaMapper instanceof PersonalAccountJpaMapper personal) {
            user = factory.createPersonalAccount(personal.getId(), personal.getFirstName(), personal.getLastName(), personal.getEmailAddress(), personal.getBirthday(), personal.getProfilePicture(), roleConverter.toRoles(personal.getUserRoles()), personal.getPasswordHash());
        }
        else if (jpaMapper instanceof BusinessAccountJpaMapper business){
            user = factory.createBusinessAccount(business.getId(), business.getFirstName(), business.getLastName(), business.getPhoneNumber(), business.getEmailAddress(), business.getBirthday(), business.getProfilePicture(), roleConverter.toRoles(business.getUserRoles()), business.getPasswordHash());
        }
        else {return null;}

        return user;
    }

    @Override
    public UserJpaMapper toUserJpaMapper(IUser user) {
        Set<RolesJpaMapper> rolesJpaMappers = roleConverter.toRolesJpa(user.getUserRoles());
        UserJpaMapper userJpaMapper = new UserJpaMapper(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmailAddress(),
                user.getBirthdate(),
                user.getProfilePicture(),
                rolesJpaMappers,
                user.getPasswordHash()
        );
        if (user instanceof PersonalAccount) {
            return PersonalAccountConverter.toUserJpaMapper(userJpaMapper);
        } else if (user instanceof BusinessAccount) {
            return BusinessAccountConverter.toUserJpaMapper(userJpaMapper);
        } else {
            return null;
        }
    }

}