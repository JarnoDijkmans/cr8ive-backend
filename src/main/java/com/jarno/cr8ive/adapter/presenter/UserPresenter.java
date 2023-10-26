package com.jarno.cr8ive.adapter.presenter;

import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.response.CreateUserResponseModel;
import com.jarno.cr8ive.business.presenter.IUserPresenter;
import org.springframework.stereotype.Service;

@Service
public class UserPresenter implements IUserPresenter {

    @Override
    public CreateUserResponseModel prepareFailView(UserCustomException e ) throws UserCustomException{
        throw e;
    }

    @Override
    public CreateUserResponseModel prepareSuccessView(CreateUserResponseModel responseModel){
        return responseModel;
    }
}

