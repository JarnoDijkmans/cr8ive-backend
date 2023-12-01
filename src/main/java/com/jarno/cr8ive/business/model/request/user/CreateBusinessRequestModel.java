package com.jarno.cr8ive.business.model.request.user;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CreateBusinessRequestModel extends CreateUserRequestModel {
    private final String phoneNumber;
    public CreateBusinessRequestModel(String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String passwordHash,  MultipartFile profilePicture){
        super(firstName, lastName, emailAddress, birthday, passwordHash, profilePicture);
        this.phoneNumber = phoneNumber;
    }
}
