package com.jarno.cr8ive.business.boundaries.output;

import com.jarno.cr8ive.domain.User;

import java.util.List;

public interface IUserGateway extends IUserExistsGateway{
    long save(User user);
    List<User> getUsersByName(String name);

}
