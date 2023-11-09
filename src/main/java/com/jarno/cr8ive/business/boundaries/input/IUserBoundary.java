package com.jarno.cr8ive.business.boundaries.input;

import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.CreateUserRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreateUserResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetAllUsersResponseModel;

public interface IUserBoundary {
    CreateUserResponseModel create (CreateUserRequestModel requestModel) throws UserCustomException;
    GetAllUsersResponseModel getUsersByName (String name) throws UserCustomException;
}
