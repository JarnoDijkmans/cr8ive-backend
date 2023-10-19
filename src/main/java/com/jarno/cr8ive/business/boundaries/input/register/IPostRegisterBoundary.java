package com.jarno.cr8ive.business.boundaries.input.register;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;

public interface IPostRegisterBoundary {
    CreatePostResponseModel create (CreatePostRequestModel requestModel) throws PostCustomException;
}
