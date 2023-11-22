package com.jarno.cr8ive.business.model.response.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder

public class LoginResponseModel {
    private String accessToken;
}
