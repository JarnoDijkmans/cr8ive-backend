package com.jarno.cr8ive.business.boundaries.input.register;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.GetUserPostsResponseModel;

import java.util.List;

public interface IPostRegisterBoundary {
    CreatePostResponseModel create (CreatePostRequestModel requestModel) throws PostCustomException;

    List<GetUserPostsResponseModel> findByUserId (long userId) throws PostCustomException;
}
