package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.input.register.IPostRegisterBoundary;
import com.jarno.cr8ive.business.boundaries.output.register.IPostRegisterGateway;
import com.jarno.cr8ive.business.converter.CreatePostConverter;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.presenter.IPostPresenter;
import com.jarno.cr8ive.domain.Post;
import lombok.AllArgsConstructor;



@AllArgsConstructor
public class PostService implements IPostRegisterBoundary {
    IPostPresenter presenter;
    IPostRegisterGateway gateway;


    @Override
    public CreatePostResponseModel create (CreatePostRequestModel requestModel) throws PostCustomException{
        Post post = CreatePostConverter.toPost(requestModel);

        if (!post.contentIsValid()) {
            return presenter.prepareFailView(new PostCustomException("Content " + post.getContent() + " is not valid"));
        }

        gateway.save(post);

        CreatePostResponseModel responseModel = new CreatePostResponseModel(post.getId());

        return presenter.prepareSuccessView(responseModel);
    }
}
