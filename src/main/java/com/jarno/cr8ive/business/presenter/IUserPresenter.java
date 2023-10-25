package com.jarno.cr8ive.business.presenter;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.CreateUserResponseModel;

public interface IUserPresenter {
    CreateUserResponseModel prepareFailView (UserCustomException e) throws UserCustomException;

    CreateUserResponseModel prepareSuccessView(CreateUserResponseModel responseModel);
}
