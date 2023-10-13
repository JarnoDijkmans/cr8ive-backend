package com.jarno.cr8ive.business.verify;

import com.jarno.cr8ive.business.interfaces.UserRepository;
import com.jarno.cr8ive.domain.User;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class Authentication {

    private UserRepository userRepo;
    /**
     *
     * @return a response when entering correct userInformation
     * @should return a success response when userInformation is correct
     * @should return a failed response when userInformation is incorrect
     */
    public User authenticate(String email, String enteredPassword ){
        try {
            User user = userRepo.authenticateUserByEmail(email);

            if (user != null && BCrypt.checkpw(enteredPassword, user.getPasswordHash())) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
