package com.jarno.cr8ive.business.boundaries.output.register;

import com.jarno.cr8ive.domain.Post;
import com.jarno.cr8ive.domain.User;

public interface IUserRegisterGateway extends IUserExistsGateway{
    long save(User user);

}
