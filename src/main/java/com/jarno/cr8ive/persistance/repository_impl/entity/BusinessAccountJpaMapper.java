package com.jarno.cr8ive.persistance.repository_impl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "business_account")
@Getter
@Setter
@NoArgsConstructor
public class BusinessAccountJpaMapper extends UserJpaMapper {

    public BusinessAccountJpaMapper(long id, String firstName, String lastName, String phoneNumber, String emailAddress, String birthday, String profilePicture, Set<RolesJpaMapper> roles, String passwordHash, String personalSpecificField){
        super(id, firstName, lastName, phoneNumber, emailAddress, birthday, profilePicture, roles, passwordHash);
    }
}
