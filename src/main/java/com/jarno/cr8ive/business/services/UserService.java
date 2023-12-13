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
import org.springframework.web.multipart.MultipartFile;

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
    public CreateUserResponseModel createAccount(CreateUserRequestModel requestModel) throws UserCustomException {
        validateEmailAddress(requestModel.getEmailAddress());

        String hashedPassword = hashPassword(requestModel.getPassword());
        Set<Roles> userRoles = createDefaultUserRole();

        String filename = getProfilePictureFilename(requestModel);

        IUser user = createUserAccount(requestModel, hashedPassword, userRoles, filename);

        storeUserInformation(user, requestModel.getProfilePicture());

        return new CreateUserResponseModel(user);
    }

    private void validateEmailAddress(String emailAddress) throws UserCustomException {
        if (repo.existsByEmailAddress(emailAddress)) {
            throw new UserCustomException("EmailAddress Already exists");
        }
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    private Set<Roles> createDefaultUserRole() {
        Roles role = Roles.builder()
                .role(RoleEnum.PERSONAL_ACCOUNT)
                .build();
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(role);
        return userRoles;
    }

    private String getProfilePictureFilename(CreateUserRequestModel requestModel) {
        if (requestModel.getProfilePicture() != null) {
            return requestModel.getProfilePicture().getOriginalFilename();
        } else {
            return "default-image-url.png";
        }
    }

    private IUser createUserAccount(CreateUserRequestModel requestModel, String hashedPassword, Set<Roles> userRoles, String filename) throws UserCustomException {
        try {
            IUser user;
            if (requestModel instanceof CreatePersonalUserRequestModel personalRequestModel) {
                user = factory.createPersonalAccount(0, personalRequestModel.getFirstName(), personalRequestModel.getLastName(), personalRequestModel.getEmailAddress(), personalRequestModel.getBirthday(), filename, userRoles, hashedPassword);
            } else if (requestModel instanceof CreateBusinessRequestModel businessRequestModel) {
                user = factory.createBusinessAccount(0, businessRequestModel.getFirstName(), businessRequestModel.getLastName(), businessRequestModel.getPhoneNumber(), businessRequestModel.getEmailAddress(), businessRequestModel.getBirthday(), filename, userRoles, hashedPassword);
            } else {
                throw new UserCustomException("Something went wrong");
            }
            return repo.save(user);
        } catch (Exception e) {
            throw new UserCustomException("Save is unsuccessful");
        }
    }

    private void storeUserInformation(IUser user, MultipartFile profilePicture) {
        if (user != null) {
            storageService.storeUserProfilePicture(user, profilePicture);
        }
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
