package com.jarno.cr8ive.domain;

import com.jarno.cr8ive.domain.user.impl.PersonalAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comments {
    private int id;
    private List<String> comment;
    private PersonalAccount personalAccount;
}
