package com.jarno.cr8ive.business.model.request.login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter

public class LoginRequestModel {
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String password;
}
