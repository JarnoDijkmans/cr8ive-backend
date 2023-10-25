package com.jarno.cr8ive.business.boundaries.input.register;

import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.CreateUserRequestModel;
import com.jarno.cr8ive.business.model.response.CreateUserResponseModel;

public interface IUserRegisterBoundary {
    CreateUserResponseModel create (CreateUserRequestModel requestModel) throws UserCustomException;
}
