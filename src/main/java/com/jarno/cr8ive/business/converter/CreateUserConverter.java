package com.jarno.cr8ive.business.converter;

import com.jarno.cr8ive.business.model.request.CreateUserRequestModel;
import com.jarno.cr8ive.domain.User;

public class CreateUserConverter {

    public static User toUserWithHash (CreateUserRequestModel requestModel, String hashedPassword){
        return User.builder()
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .phoneNumber(requestModel.getPhoneNumber())
                .emailAddress(requestModel.getEmailAddress())
                .birthday(requestModel.getBirthday())
                .profilePicture(requestModel.getProfilePicture())
                .role(requestModel.getRole())
                .location(requestModel.getCountry())
                .passwordHash(hashedPassword)
                .build();
    }
}
