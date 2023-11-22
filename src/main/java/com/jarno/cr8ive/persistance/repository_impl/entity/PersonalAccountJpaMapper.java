package com.jarno.cr8ive.persistance.repository_impl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Entity
@Table(name = "personal_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalAccountJpaMapper extends UserJpaMapper {

    private String personalSpecificField;
    public PersonalAccountJpaMapper(long id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String profilePicture, String bio, String location, String currentJob, List<UserJpaMapper> following, List<UserJpaMapper> followers, Set<RolesJpaMapper> roles, String passwordHash, String personalSpecificField){
        super(id, firstName, lastName, phoneNumber, emailAddress, birthday, profilePicture, bio, location, currentJob, following, followers, roles, passwordHash);
        this.personalSpecificField = personalSpecificField;
    }

    public PersonalAccountJpaMapper(long id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String profilePicture, Set<RolesJpaMapper> roles, String passwordHash, String personalSpecificField){
        super(id, firstName, lastName, phoneNumber, emailAddress, birthday, profilePicture, roles, passwordHash);
        this.personalSpecificField = personalSpecificField;
    }
}
