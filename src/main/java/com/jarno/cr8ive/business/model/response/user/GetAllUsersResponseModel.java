package com.jarno.cr8ive.business.model.response.user;

import com.jarno.cr8ive.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class GetAllUsersResponseModel {
    List<User> user;
}
