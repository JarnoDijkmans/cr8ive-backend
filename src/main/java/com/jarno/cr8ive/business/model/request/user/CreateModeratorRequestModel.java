package com.jarno.cr8ive.business.model.request.user;

import org.springframework.web.multipart.MultipartFile;

public class CreateModeratorRequestModel extends CreateUserRequestModel{

    public CreateModeratorRequestModel(String firstName, String lastName, String emailAddress, String birthdate, String passwordHash, MultipartFile profilePicture){
        super(firstName, lastName, emailAddress, birthdate, passwordHash, profilePicture);
    }
}
