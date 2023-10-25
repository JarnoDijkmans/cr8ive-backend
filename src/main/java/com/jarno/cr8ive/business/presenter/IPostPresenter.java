package com.jarno.cr8ive.business.presenter;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.GetUserPostsResponseModel;

import java.util.List;

public interface IPostPresenter {
    CreatePostResponseModel prepareFailView (PostCustomException e) throws PostCustomException;

    CreatePostResponseModel prepareSuccessView(CreatePostResponseModel responseModel);

    List<GetUserPostsResponseModel> prepareFailViewForFindByUserId (PostCustomException e) throws PostCustomException;

}
