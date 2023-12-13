package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.services.IAuthService;
import com.jarno.cr8ive.configuration.security.token.AccessToken;
import com.jarno.cr8ive.configuration.security.token.impl.AccessTokenEncoderDecoderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService implements IAuthService {
    private final AccessTokenEncoderDecoderImpl tokenEncoderDecoder;

    @Autowired
    public AuthService(AccessTokenEncoderDecoderImpl tokenEncoderDecoder) {
        this.tokenEncoderDecoder = tokenEncoderDecoder;
    }

    public Set<String> extractRolesFromToken(String accessTokenEncoded) {
        AccessToken decodedToken = tokenEncoderDecoder.decode(accessTokenEncoded);
        return decodedToken.getRoles();
    }
    public long extractUserIdFromToken(String accessTokenEncoded) {
        AccessToken decodedToken = tokenEncoderDecoder.decode(accessTokenEncoded);
        return decodedToken.getUserId();
    }
}
