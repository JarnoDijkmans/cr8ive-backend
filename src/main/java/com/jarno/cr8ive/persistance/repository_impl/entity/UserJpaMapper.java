package com.jarno.cr8ive.persistance.repository_impl.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class UserJpaMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Length(min = 3, max = 20)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name = "bio")
    private String bio;
    @Column(name = "location")
    private String location;
    @Column(name = "current_job")
    private String currentJob;
    @ManyToMany
    @JoinTable(
            name = "User_Follows_User",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private List<UserJpaMapper> following;
    @ManyToMany(mappedBy = "following")
    private List<UserJpaMapper> followers;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<RolesJpaMapper> userRoles;
    @Column(name = "password_hash")
    private String passwordHash;

    public UserJpaMapper(long id, String firstName, String lastName, String emailAddress, String birthdate, String profilePicture, Set<RolesJpaMapper> roles, String passwordHash) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthday = birthdate;
        this.profilePicture = profilePicture;
        this.userRoles = roles;
        this.passwordHash = passwordHash;
    }
}
