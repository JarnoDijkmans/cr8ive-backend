package com.jarno.cr8ive.business.boundaries.services;

import java.util.Set;

public interface IAuthService {
    Set<String> extractRolesFromToken(String accessTokenEncoded);
    long extractUserIdFromToken(String accessTokenEncoded);
}
