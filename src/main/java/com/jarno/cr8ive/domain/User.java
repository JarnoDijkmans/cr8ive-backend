package com.jarno.cr8ive.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Setter
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String birthday;
    private String profilePicture;
    private int role;
    private String location;
    private String passwordHash;
    private String bio;
    private String currentJob;
    private List<UserPreferences> interests;
    private List<Long> followList;
    private List<Long> followingList;


    public boolean firstNameIsValid (){
        return firstName != null;
    }
}

