package com.jarno.cr8ive.business.model.response.post;

import com.jarno.cr8ive.domain.user.IUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateUserResponseModel {
    private IUser user;
}
