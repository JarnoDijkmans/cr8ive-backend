package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.IUserRepository;
import com.jarno.cr8ive.business.exeption.InvalidCredentialsException;
import com.jarno.cr8ive.business.model.request.login.LoginRequestModel;
import com.jarno.cr8ive.business.model.response.login.LoginResponseModel;
import com.jarno.cr8ive.configuration.security.token.AccessTokenEncoder;
import com.jarno.cr8ive.domain.RoleEnum;
import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.domain.user.IUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServiceTest {
    private IUserRepository userRepository = mock(IUserRepository.class);
    private AccessTokenEncoder accessTokenEncoder = mock(AccessTokenEncoder.class);
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        loginService = new LoginService(userRepository, accessTokenEncoder);
    }

    @Test
    void testLogin_ValidCredentials() {
        // Mock user data
        String emailAddress = "jarnodijkmans@gmail.com";
        String password = "test123";
        String encodedPassword = "$2a$10$f/ZdtHZ8rHTp1gOeKfb9C.oknp0zTkdwTH1X3DkStWcKkY4wbQPBe"; // Replace with hashed password

        List<Roles> userRoles = new ArrayList<>();
        userRoles.add(Roles.builder().id(1L).role(RoleEnum.PERSONAL_ACCOUNT).build());

        IUser mockUser = mock(IUser.class);
        when(mockUser.getId()).thenReturn(1L);
        when(mockUser.getPasswordHash()).thenReturn(encodedPassword);


        when(userRepository.findUserByEmailAddress(emailAddress)).thenReturn(mockUser);
        when(accessTokenEncoder.encode(any())).thenReturn("mocked_access_token");

        LoginRequestModel loginRequest = new LoginRequestModel(emailAddress, password);
        LoginResponseModel response = loginService.login(loginRequest);

        // Assertions
        assertNotNull(response);
        assertEquals("mocked_access_token", response.getAccessToken());
    }

    @Test
    void testLogin_InvalidCredentials() {
        // Mock user data
        String emailAddress = "jarnodijkmans@gmail.com";
        String password = "wrong_password";
        String encodedPassword = "$2a$10$f/ZdtHZ8rHTp1gOeKfb9C.oknp0zTkdwTH1X3DkStWcKkY4wbQPBe";
        IUser mockUser = mock(IUser.class);
        when(mockUser.getId()).thenReturn(1L);
        when(mockUser.getPasswordHash()).thenReturn(encodedPassword);

        // Mock repository behavior
        when(userRepository.findUserByEmailAddress(emailAddress)).thenReturn(mockUser);

        // Test login with invalid credentials
        LoginRequestModel loginRequest = new LoginRequestModel(emailAddress, password);

        // Assertions for InvalidCredentialsException
        assertThrows(InvalidCredentialsException.class, () -> {
            loginService.login(loginRequest);
        });
    }
}

