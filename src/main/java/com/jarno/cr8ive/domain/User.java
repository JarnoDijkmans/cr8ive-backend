package com.jarno.cr8ive.domain;

import lombok.*;

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
    private String bio;
    private String location;
    private String currentJob;
    private List<String> interests;
    private List<Long> followList;
    private List<Long> followingList;
    private int role;
    private String passwordHash;
    private String salt;
}
