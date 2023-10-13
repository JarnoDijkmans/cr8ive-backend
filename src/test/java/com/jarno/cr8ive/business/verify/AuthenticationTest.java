package com.jarno.cr8ive.business.verify;

import com.jarno.cr8ive.business.interfaces.PostRepository;
import com.jarno.cr8ive.business.interfaces.UserRepository;
import com.jarno.cr8ive.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationTest {

    /**
     * @verifies return a success response when userInformation is correct
     * @see Authentication#authenticate(String, String)
     */
    /**
    @Test
    public void authenticate_shouldReturnASuccessResponseWhenUserInformationIsCorrect() throws Exception {
        // Arrange
        UserRepository userRepo = mock(UserRepository.class);
        Authentication authentication = new Authentication(userRepo);
        String email = "testuser@example.com";
        String password = "testpassword"; // This should be hashed and stored in your repository


        when(userRepo.authenticateUserByEmail(email)).thenReturn(mockUser);
        when(BCrypt.checkpw(password, mockUser.getPasswordHash())).thenReturn(true);

        // Act
        User authenticatedUser = authentication.authenticate(email, password);

        // Assert
        assertNotNull(authenticatedUser);
    }

    /**
     * @verifies return a failed response when userInformation is incorrect
     * @see Authentication#authenticate(String, String)
     */
    @Test
    public void authenticate_shouldReturnAFailedResponseWhenUserInformationIsIncorrect() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}