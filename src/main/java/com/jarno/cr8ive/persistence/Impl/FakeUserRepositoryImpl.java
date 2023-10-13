package com.jarno.cr8ive.persistence.Impl;

import com.jarno.cr8ive.business.interfaces.UserRepository;
import com.jarno.cr8ive.domain.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeUserRepositoryImpl implements UserRepository {
    private final List<User> savedUser;
    private static long NEXT_ID = 1;

    public FakeUserRepositoryImpl() {
        this.savedUser = new ArrayList<>();
    }


    @Override
    public boolean existsById(long userId){
        return this.savedUser
                .stream()
                .anyMatch(user -> user.getId() == (userId));
    }
    @Override
    public User saveUser(User user){
        user.setId(NEXT_ID);
        setNextId(NEXT_ID);
        existsById(user.getId());
        this.savedUser.add(user);
        return user;
    }

    public void setNextId(Long id) {
        NEXT_ID++;
    }

    public User authenticateUserByEmail(String email) {
        for (User user : savedUser) {
            if (user.getEmailAddress().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
