package com.jarno.cr8ive.business.boundaries.repository;

import com.jarno.cr8ive.business.exeption.IUserExistsGateway;
import com.jarno.cr8ive.domain.user.IUser;

import java.util.List;

public interface IUserRepository extends IUserExistsGateway {
    IUser save(IUser user);
    List<IUser> getUsersByName(String name);
    IUser findUserByEmailAddress (String emailAddress);
    IUser findUserById (long id);
    boolean existsByEmailAddress(String emailAddress);

}
