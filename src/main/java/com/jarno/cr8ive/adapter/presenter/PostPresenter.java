package com.jarno.cr8ive.adapter.presenter;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.presenter.IPostPresenter;

import java.time.LocalDateTime;

public class PostPresenter implements IPostPresenter {

    @Override
    public CreatePostResponseModel prepareFailView(PostCustomException e ) throws PostCustomException{
        throw e;
    }

    @Override
    public CreatePostResponseModel prepareSuccessView(CreatePostResponseModel responseModel){
        return responseModel;
    }
}
