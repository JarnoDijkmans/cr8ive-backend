package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.input.IUserBoundary;
import com.jarno.cr8ive.business.boundaries.output.IUserGateway;
import com.jarno.cr8ive.business.converter.CreateUserConverter;
import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.CreateUserRequestModel;
import com.jarno.cr8ive.business.model.response.CreateUserResponseModel;
import com.jarno.cr8ive.domain.User;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserService implements IUserBoundary {
    IUserGateway gateway;


    @Override
    public CreateUserResponseModel create (CreateUserRequestModel requestModel) throws UserCustomException {

        long result = 0;
        try {
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(requestModel.getPassword(), salt);
            User user = CreateUserConverter.toUserWithHash(requestModel, hashedPassword);

            if (!user.firstNameIsValid()) {
               throw new UserCustomException("FirstName " + user.getFirstName() + " is not valid");
            }
            result = gateway.save(user);
        } catch (Exception e){
            throw new UserCustomException("Save was unsuccessful");
        }
        return new CreateUserResponseModel(result);
    }
}
