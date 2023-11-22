package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.IUserRepository;
import com.jarno.cr8ive.business.boundaries.services.IUserService;
import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.user.CreateBusinessRequestModel;
import com.jarno.cr8ive.business.model.request.user.CreatePersonalUserRequestModel;
import com.jarno.cr8ive.business.model.request.user.CreateUserRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreateUserResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetAllUsersResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetUserResponseModel;
import com.jarno.cr8ive.domain.RoleEnum;
import com.jarno.cr8ive.domain.Roles;
import com.jarno.cr8ive.domain.factory.IUserFactory;
import com.jarno.cr8ive.domain.user.IUser;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Service
public class UserService implements IUserService {
    IUserRepository repo;
    IUserFactory factory;
    private StorageService storageService;

    @Override
    public CreateUserResponseModel createPersonalAccount (CreateUserRequestModel requestModel) throws UserCustomException {

        long result;
        try {
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(requestModel.getPassword(), salt);

            Roles role = Roles.builder()
                    .role(RoleEnum.PERSONAL_ACCOUNT)
                    .build();
            Set<Roles> userRoles = new HashSet<>();
            userRoles.add(role);

            IUser user;
            if (requestModel instanceof CreatePersonalUserRequestModel personalRequestModel){
                user = factory.CreatePersonalAccount(0, personalRequestModel.getFirstName(), personalRequestModel.getLastName(), personalRequestModel.getPhoneNumber(), personalRequestModel.getEmailAddress(), personalRequestModel.getBirthday(), personalRequestModel.getProfilePicture().getOriginalFilename(), userRoles, hashedPassword, personalRequestModel.getPersonalSpecificField());
            }else if (requestModel instanceof CreateBusinessRequestModel businessRequestModel){
                user = factory.CreateBusinessAccount(0, businessRequestModel.getFirstName(), businessRequestModel.getLastName(), businessRequestModel.getPhoneNumber(), businessRequestModel.getEmailAddress(), businessRequestModel.getBirthday(), businessRequestModel.getProfilePicture().getOriginalFilename(), userRoles, hashedPassword);
            }
            else {
                throw new UserCustomException("Something went wrong");
            }
            result = repo.save(user);
            if (result != 0) {
                storageService.storeUserProfilePicture(result, requestModel.getProfilePicture());
            }
        } catch (Exception e){
            throw new UserCustomException("Save was unsuccessful");
        }
        return new CreateUserResponseModel(result);
    }

    @Override
    public GetAllUsersResponseModel getUsersByName (String name) throws UserCustomException{
        List<IUser> users;
        try{
            users = repo.getUsersByName(name);
        }
        catch(Exception e){
            throw new UserCustomException("Problem with finding users");
        }
        return new GetAllUsersResponseModel(users);
    }

    @Override
    public GetUserResponseModel getUserById(long id) throws UserCustomException{
        IUser user;
        try {
            user = repo.findUserById(id);
        } catch(Exception e){
            throw new UserCustomException("Problem with finding user");
        }
        return new GetUserResponseModel(user);
    }
}
