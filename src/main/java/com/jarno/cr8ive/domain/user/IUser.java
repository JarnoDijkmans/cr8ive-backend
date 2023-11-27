package com.jarno.cr8ive.domain.user;

import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.domain.UserPreferences;

import java.util.List;
import java.util.Set;

public interface IUser {
    long getId();
    String getFirstName();
    String getLastName();
    String getPhoneNumber();
    String getEmailAddress();
    String getBirthdate();
    String getProfilePicture();
    Set<Roles> getUserRoles();
    String getLocation();
    String getPasswordHash();
    String getBio();
    String getCurrentJob();
    List<UserPreferences> getInterests();
    List<Long> getFollowList();
    List<Long> getFollowingList();
}