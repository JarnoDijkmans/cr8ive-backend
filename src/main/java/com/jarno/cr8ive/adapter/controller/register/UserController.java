package com.jarno.cr8ive.adapter.controller.register;


import com.jarno.cr8ive.business.boundaries.input.register.IUserRegisterBoundary;
import com.jarno.cr8ive.business.exeption.UserCustomException;
import com.jarno.cr8ive.business.model.request.CreateUserRequestModel;
import com.jarno.cr8ive.business.model.response.CreateUserResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final IUserRegisterBoundary inputBoundary;

    @Autowired
    public UserController(IUserRegisterBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    @PostMapping
    public CreateUserResponseModel create (@RequestBody CreateUserRequestModel requestModel) throws UserCustomException {
        return this.inputBoundary.create(requestModel);
    }
}
