package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.boundaries.services.ILoginService;
import com.jarno.cr8ive.business.model.request.login.LoginRequestModel;
import com.jarno.cr8ive.business.model.response.login.LoginResponseModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tokens")
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

    private final ILoginService service;

    @Autowired
    public LoginController(ILoginService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoginResponseModel> login(@RequestBody @Valid LoginRequestModel loginRequest) {
        LoginResponseModel loginResponse = service.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
