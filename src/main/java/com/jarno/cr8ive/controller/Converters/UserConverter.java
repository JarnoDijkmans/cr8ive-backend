package com.jarno.cr8ive.controller.Converters;

import com.jarno.cr8ive.controller.request.post.CreatePostRequest;
import com.jarno.cr8ive.controller.request.user.CreateUserRequest;
import com.jarno.cr8ive.controller.response.post.CreatePostResponse;
import com.jarno.cr8ive.controller.response.user.CreateUserResponse;
import com.jarno.cr8ive.domain.Post;
import com.jarno.cr8ive.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public static User toUser(CreateUserRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .emailAddress(request.getEmailAddress())
                .birthday(request.getBirthday())
                .profilePicture(request.getProfilePicture())
                .role(request.getRole())
                .passwordHash(request.getPasswordHash())
                .salt(request.getSalt())
                .build();
    }

    public static CreateUserResponse toCreateUserResponse(User user) {
        return CreateUserResponse.builder()
                .id(user.getId())
                .build();
    }
}
