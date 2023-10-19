package com.jarno.cr8ive.adapter.controller.register;

import com.jarno.cr8ive.business.boundaries.input.register.IPostRegisterBoundary;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
public class PostController {
    IPostRegisterBoundary inputBoundary;

    @PostMapping
    public CreatePostResponseModel create (@RequestBody CreatePostRequestModel requestModel) throws PostCustomException {
        return this.inputBoundary.create(requestModel);
    }
}
