package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.boundaries.services.IPostService;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.post.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetUserPostsResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {
    private final IPostService service;

    @Autowired
    public PostController(IPostService service) {
        this.service = service;
    }

    @PostMapping
    public CreatePostResponseModel create ( @RequestParam("content") List<MultipartFile> content, @RequestParam("description") String description, @RequestParam("hashtagIds") List<Integer> hashtagIds, @RequestParam("userId") Long userId) throws PostCustomException {
        CreatePostRequestModel requestModel = new CreatePostRequestModel(content, description, hashtagIds, userId);
        return this.service.create(requestModel);
    }

    @GetMapping("{id}")
    public GetUserPostsResponseModel getPostsByUserId(@PathVariable("id") long userId) throws PostCustomException {
        return this.service.findByUserId(userId);
    }

}
