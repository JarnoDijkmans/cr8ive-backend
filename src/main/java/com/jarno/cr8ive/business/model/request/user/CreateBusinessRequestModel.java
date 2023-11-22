package com.jarno.cr8ive.business.model.request.user;

import org.springframework.web.multipart.MultipartFile;

public class CreateBusinessRequestModel extends CreateUserRequestModel {
    public CreateBusinessRequestModel(String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, MultipartFile profilePicture, String passwordHash){
        super(firstName, lastName, phoneNumber, emailAddress, birthday, passwordHash, profilePicture);
    }
}
