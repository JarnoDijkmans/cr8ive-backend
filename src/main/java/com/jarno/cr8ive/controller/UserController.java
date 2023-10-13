package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.ServiceImpl.UserService;
import com.jarno.cr8ive.controller.Converters.UserConverter;
import com.jarno.cr8ive.controller.request.user.CreateUserRequest;
import com.jarno.cr8ive.controller.response.user.CreateUserResponse;
import com.jarno.cr8ive.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request){
        User user = UserConverter.toUser(request);
        User createdUser = userService.createUser(user);
        CreateUserResponse response = UserConverter.toCreateUserResponse(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
