package com.jarno.cr8ive.business.boundaries.input;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetUserPostsResponseModel;

import java.util.List;

public interface IPostBoundary {
    CreatePostResponseModel create (CreatePostRequestModel requestModel) throws PostCustomException;

    List<GetUserPostsResponseModel> findByUserId (long userId) throws PostCustomException;
}
