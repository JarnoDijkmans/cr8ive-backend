package com.jarno.cr8ive.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CvItems {
    private int id;
    private String title;
    private String personalInformation;
    private String workExperience;
    private String education;
    private String skills;
    private String profilePicture;
    private String media;
    private Date creationDate;
}
