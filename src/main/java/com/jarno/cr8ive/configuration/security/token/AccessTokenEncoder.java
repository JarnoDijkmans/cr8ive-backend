package com.jarno.cr8ive.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
