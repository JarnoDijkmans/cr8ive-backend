package com.jarno.cr8ive.domain.factory.impl;

import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.domain.UserPreferences;
import com.jarno.cr8ive.domain.factory.IUserFactory;
import com.jarno.cr8ive.domain.user.impl.BusinessAccount;
import com.jarno.cr8ive.domain.user.impl.PersonalAccount;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class UserFactory implements IUserFactory {
    @Override
    public PersonalAccount CreatePersonalAccount(long id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String profilePicture, Set<Roles> userRoles, String passwordHash, String personalSpecificField){
        return new PersonalAccount(id, firstName, lastName, phoneNumber, emailAddress,  birthday,  profilePicture, userRoles, passwordHash, personalSpecificField);
    }

    @Override
    public BusinessAccount CreateBusinessAccount(long id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String profilePicture, Set<Roles> userRoles, String passwordHash){
        return new BusinessAccount(id, firstName, lastName, phoneNumber, emailAddress,  birthday,  profilePicture, userRoles, passwordHash);
    }

    @Override
    public BusinessAccount BusinessAccount(long id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String profilePicture, Set<Roles> userRoles, String location, String passwordHash, String bio, String currentJob, List<UserPreferences> interests, List<Long> followList, List<Long> followingList){
        return new BusinessAccount(id, firstName, lastName, phoneNumber, emailAddress,  birthday,  profilePicture, userRoles,  location,  passwordHash,  bio,  currentJob, interests, followList, followingList);
    }
}
