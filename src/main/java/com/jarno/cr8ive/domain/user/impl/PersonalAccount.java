package com.jarno.cr8ive.domain.user.impl;

import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.domain.UserPreferences;
import com.jarno.cr8ive.domain.user.IUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
public class PersonalAccount implements IUser {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String emailAddress;
    private final String birthday;
    private final String profilePicture;
    private final Set<Roles> userRoles;
    private String location;
    private final String passwordHash;
    private String bio;
    private String currentJob;
    private List<UserPreferences> interests;
    private List<Long> followList;
    private List<Long> followingList;
    @Getter
    private final String personalSpecificField;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getBirthday() {
        return birthday;
    }

    @Override
    public String getProfilePicture() {
        return profilePicture;
    }

    @Override
    public Set<Roles> getUserRoles() {
        return userRoles;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String getBio() {
        return bio;
    }

    @Override
    public String getCurrentJob() {
        return currentJob;
    }

    @Override
    public List<UserPreferences> getInterests() {
        return interests;
    }

    @Override
    public List<Long> getFollowList() {
        return followList;
    }

    @Override
    public List<Long> getFollowingList() {
        return followingList;
    }

}

