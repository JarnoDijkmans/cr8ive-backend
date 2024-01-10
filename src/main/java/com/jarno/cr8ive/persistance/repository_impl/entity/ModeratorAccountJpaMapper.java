package com.jarno.cr8ive.persistance.repository_impl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "moderator_account")
@Getter
@Setter
@NoArgsConstructor
public class ModeratorAccountJpaMapper extends UserJpaMapper {
    public ModeratorAccountJpaMapper(long id, String firstName, String lastName, String emailAddress, String birthdate, String profilePicture, Set<RolesJpaMapper> roles, String passwordHash){
        super(id, firstName, lastName, emailAddress, birthdate, profilePicture, roles, passwordHash);
    }
}
