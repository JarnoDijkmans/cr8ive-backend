package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.IUserRepository;
import com.jarno.cr8ive.business.boundaries.services.ILoginService;
import com.jarno.cr8ive.business.exeption.InvalidCredentialsException;
import com.jarno.cr8ive.business.model.request.login.LoginRequestModel;
import com.jarno.cr8ive.business.model.response.login.LoginResponseModel;
import com.jarno.cr8ive.configuration.security.token.AccessTokenEncoder;
import com.jarno.cr8ive.configuration.security.token.impl.AccessTokenImpl;
import com.jarno.cr8ive.domain.user.IUser;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginService implements ILoginService {
    IUserRepository repo;
    AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponseModel login(LoginRequestModel loginRequest){
        IUser user = repo.findUserByEmailAddress(loginRequest.getEmailAddress());
            if (user == null || !matchesPassword(loginRequest.getPassword(), user.getPasswordHash())){
                throw new InvalidCredentialsException();
            }
        String accessToken = generateAccessToken(user);
        return LoginResponseModel.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }

    private String generateAccessToken(IUser user) {
        long userId = user.getId();
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getFirstName(), userId, roles));
    }
}
