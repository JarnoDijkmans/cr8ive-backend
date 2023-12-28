package com.jarno.cr8ive.business.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserResponseModel {
    private long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
}
