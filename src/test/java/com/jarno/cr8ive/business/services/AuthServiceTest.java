package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.configuration.security.token.AccessToken;
import com.jarno.cr8ive.configuration.security.token.impl.AccessTokenEncoderDecoderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthServiceTest {
    @Mock
    AccessTokenEncoderDecoderImpl tokenEncoderDecoder;
    @Mock
    AccessToken accessToken;
    @InjectMocks
    AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExtractRolesFromToken() {
        String mockToken = "mockToken";
        Set<String> mockRoles = Collections.singleton("mockRole");
        when(tokenEncoderDecoder.decode(mockToken)).thenReturn(accessToken);
        when(accessToken.getRoles()).thenReturn(mockRoles);

        Set<String> roles = authService.extractRolesFromToken(mockToken);

        assertEquals(mockRoles, roles);
    }


    @Test
    void testExtractUserIdFromToken() {
        String mockToken = "mockToken";
        long mockUserId = 1L;
        when(tokenEncoderDecoder.decode(mockToken)).thenReturn(accessToken);
        when(accessToken.getUserId()).thenReturn(mockUserId);

        long userId = authService.extractUserIdFromToken(mockToken);

        assertEquals(mockUserId, userId);
    }

    @Test
    void testExtractTokenFromAuthorizationHeader() {
        // Arrange
        String authorizationHeader = "Bearer yourAccessToken";
        //Act
        String result = authService.extractTokenFromAuthorizationHeader(authorizationHeader);
        //Assert
        assertEquals("yourAccessToken", result);
    }
}
