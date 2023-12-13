package com.jarno.cr8ive.business.boundaries.services;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.post.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetPostByPostIdResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetUserPostsResponseModel;

public interface IPostService {
    CreatePostResponseModel create (CreatePostRequestModel requestModel) throws PostCustomException;

    GetUserPostsResponseModel findByUserId (long userId) throws PostCustomException;

    GetPostByPostIdResponseModel findByPostId(long postId) throws PostCustomException;

    void deleteUserPost (long postId) throws PostCustomException;

    boolean userOwnsPost (long postId, long userId);
}
