package com.jarno.cr8ive.controller;


import com.jarno.cr8ive.business.boundaries.input.IUserBoundary;
import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.CreateUserRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreateUserResponseModel;
import com.jarno.cr8ive.business.model.response.user.GetAllUsersResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final IUserBoundary inputBoundary;

    @Autowired
    public UserController(IUserBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    @PostMapping
    public CreateUserResponseModel create (@RequestBody CreateUserRequestModel requestModel) throws UserCustomException {
        return this.inputBoundary.create(requestModel);
    }

    @GetMapping("{name}")
    public GetAllUsersResponseModel getUsersByName (@PathVariable("name") String name) throws UserCustomException {
        return this.inputBoundary.getUsersByName(name);
    }
}
