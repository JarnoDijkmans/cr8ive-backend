package com.jarno.cr8ive.business.model.response.user;

import com.jarno.cr8ive.domain.user.IUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserResponseModel {
    private IUser user;
}
