package com.jarno.cr8ive.domain.factory;

import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.domain.UserPreferences;
import com.jarno.cr8ive.domain.user.IUser;

import java.util.List;
import java.util.Set;

public interface IUserFactory {
    IUser CreatePersonalAccount(long id, String firstName, String lastName, String emailAddress, String birthday, String profilePicture, Set<Roles> userRoles, String passwordHash);

    IUser CreateBusinessAccount(long id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String profilePicture, Set<Roles> userRoles, String passwordHash);
    IUser BusinessAccount(long id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String profilePicture, Set<Roles> userRoles, String location, String passwordHash, String bio, String currentJob, List<UserPreferences> interests, List<Long> followList, List<Long> followingList);
}
