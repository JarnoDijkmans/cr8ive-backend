package com.jarno.cr8ive.business.model.request.user;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreateUserRequestModel {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String birthday;
    private String password;
    private MultipartFile profilePicture;
}
