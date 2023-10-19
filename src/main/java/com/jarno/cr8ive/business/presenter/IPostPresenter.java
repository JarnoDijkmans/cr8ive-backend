package com.jarno.cr8ive.business.presenter;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;

public interface IPostPresenter {
    CreatePostResponseModel prepareFailView (PostCustomException e) throws PostCustomException;

    CreatePostResponseModel prepareSuccessView(CreatePostResponseModel responseModel);
}
