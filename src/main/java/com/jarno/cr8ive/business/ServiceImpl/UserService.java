package com.jarno.cr8ive.business.ServiceImpl;

import com.jarno.cr8ive.business.interfaces.UserRepository;
import com.jarno.cr8ive.business.interfaces.UserServiceInterface;
import com.jarno.cr8ive.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        {
            return userRepository.saveUser(user);
        }
    }
}
