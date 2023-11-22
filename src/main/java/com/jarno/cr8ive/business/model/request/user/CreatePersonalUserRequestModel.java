package com.jarno.cr8ive.business.model.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class CreatePersonalUserRequestModel extends CreateUserRequestModel {
    private String personalSpecificField;

    public CreatePersonalUserRequestModel(String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, MultipartFile profilePicture, String passwordHash, String personalSpecificField){
        super(firstName, lastName, phoneNumber, emailAddress, birthday, passwordHash, profilePicture);
        this.personalSpecificField = personalSpecificField;
    }
}
