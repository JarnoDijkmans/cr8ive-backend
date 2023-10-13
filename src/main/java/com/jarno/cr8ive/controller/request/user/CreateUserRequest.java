package com.jarno.cr8ive.controller.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NotBlank
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String birthday;
    private String profilePicture;
    private int role;
    private String country;
    private String passwordHash;
    private String salt;
}
