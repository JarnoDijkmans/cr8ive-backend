package com.jarno.cr8ive.business.boundaries.input;

import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.CreateUserRequestModel;
import com.jarno.cr8ive.business.model.response.CreateUserResponseModel;

public interface IUserBoundary {
    CreateUserResponseModel create (CreateUserRequestModel requestModel) throws UserCustomException;
}
