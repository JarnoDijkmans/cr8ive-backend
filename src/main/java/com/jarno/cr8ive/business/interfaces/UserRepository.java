package com.jarno.cr8ive.business.interfaces;

import com.jarno.cr8ive.domain.User;

public interface UserRepository {
    boolean existsById(long userId);
    User saveUser(User user);
    User authenticateUserByEmail(String email);
}
