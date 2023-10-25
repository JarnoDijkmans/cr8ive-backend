package com.jarno.cr8ive.adapter.presenter;

import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.GetUserPostsResponseModel;
import com.jarno.cr8ive.business.presenter.IPostPresenter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostPresenter implements IPostPresenter {

    @Override
    public CreatePostResponseModel prepareFailView(PostCustomException e ) throws PostCustomException{
        throw e;
    }

    @Override
    public CreatePostResponseModel prepareSuccessView(CreatePostResponseModel responseModel){
        return responseModel;
    }

    @Override
    public List<GetUserPostsResponseModel> prepareFailViewForFindByUserId(PostCustomException e) throws PostCustomException{
        throw e;
    }
}
