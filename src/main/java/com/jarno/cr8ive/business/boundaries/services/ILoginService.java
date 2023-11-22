package com.jarno.cr8ive.business.boundaries.services;

import com.jarno.cr8ive.business.model.request.login.LoginRequestModel;
import com.jarno.cr8ive.business.model.response.login.LoginResponseModel;

public interface ILoginService {
    LoginResponseModel login(LoginRequestModel loginRequest);
}
