package com.jarno.cr8ive.business.boundaries.output;

import com.jarno.cr8ive.domain.User;

public interface IUserGateway extends IUserExistsGateway{
    long save(User user);

}
