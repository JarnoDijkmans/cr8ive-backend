package com.jarno.cr8ive.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserPreference {
    private Set<String>contentTypes;
    private Set<String>hashtags;
    private Set<String>usersToFollow;
}
