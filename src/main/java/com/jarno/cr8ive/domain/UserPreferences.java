package com.jarno.cr8ive.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserPreferences {
    private Set<Integer> hashtagIds;
    private Set<Integer>usersToFollow;
}
