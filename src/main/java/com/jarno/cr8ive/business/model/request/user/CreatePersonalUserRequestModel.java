package com.jarno.cr8ive.business.model.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class CreatePersonalUserRequestModel extends CreateUserRequestModel {

    public CreatePersonalUserRequestModel(String firstName, String lastName, String emailAddress, String birthdate, String passwordHash, MultipartFile profilePicture){
        super(firstName, lastName, emailAddress, birthdate, passwordHash, profilePicture);
    }
}
