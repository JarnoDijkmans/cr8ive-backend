package com.jarno.cr8ive.business.model.request;

import lombok.*;

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
    private String profilePicture;
    private int role;
    private String country;
    private String password;
}
