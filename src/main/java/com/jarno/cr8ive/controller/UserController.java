package com.jarno.cr8ive.controller;


import com.jarno.cr8ive.business.boundaries.services.IUserService;
import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.user.CreateBusinessRequestModel;
import com.jarno.cr8ive.business.model.request.user.CreateModeratorRequestModel;
import com.jarno.cr8ive.business.model.request.user.CreatePersonalUserRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreateUserResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetAllUsersResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetUserResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public CreateUserResponseModel createPersonalAccount (@RequestPart(required = false) MultipartFile profilePicture, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("emailAddress") String emailAddress, @RequestParam("birthdate") String birthdate, @RequestParam("password") String password) throws UserCustomException {
        CreatePersonalUserRequestModel requestModel = new CreatePersonalUserRequestModel(firstName, lastName, emailAddress, birthdate, password, profilePicture);
        return this.service.createAccount(requestModel);
    }

    @PostMapping("/register/business")
    public CreateUserResponseModel createBusinessAccount (@RequestPart("profilePicture") MultipartFile profilePicture, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("emailAddress") String emailAddress, @RequestParam("birthday") String birthday, @RequestParam("role") int role, @RequestParam("country") String country, @RequestParam("password") String password) throws UserCustomException {
        CreateBusinessRequestModel requestModel = new CreateBusinessRequestModel(firstName, lastName, phoneNumber, emailAddress, birthday, password, profilePicture);
        return this.service.createAccount(requestModel);
    }

    @PostMapping("/register/moderator")
    public CreateUserResponseModel createModeratorAccount (@RequestPart(required = false) MultipartFile profilePicture, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("emailAddress") String emailAddress, @RequestParam("birthdate") String birthdate, @RequestParam("password") String password) throws UserCustomException {
        CreateModeratorRequestModel requestModel = new CreateModeratorRequestModel(firstName, lastName, emailAddress, birthdate, password, profilePicture);
        return this.service.createAccount(requestModel);
    }

    @GetMapping("{name}")
    public GetAllUsersResponseModel getUsersByName (@PathVariable("name") String name) throws UserCustomException {
        return this.service.getUsersByName(name);
    }

    @GetMapping("/byId/{id}")
    public GetUserResponseModel getUserById (@PathVariable("id") long id) throws UserCustomException {
        return this.service.getUserById(id);
    }

}
