package com.jarno.cr8ive.business.boundaries.services;

import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.user.CreateUserRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreateUserResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetAllUsersResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetUserResponseModel;

public interface IUserService {
    CreateUserResponseModel createAccount (CreateUserRequestModel requestModel) throws UserCustomException;
    GetAllUsersResponseModel getUsersByName (String name) throws UserCustomException;
    GetUserResponseModel getUserById (long id) throws UserCustomException;
}
