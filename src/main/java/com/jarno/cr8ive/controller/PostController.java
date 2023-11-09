package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.boundaries.input.IPostBoundary;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetUserPostsResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {
    private final IPostBoundary inputBoundary;

    @Autowired
    public PostController(IPostBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    @PostMapping
    public CreatePostResponseModel create ( @RequestParam("content") List<MultipartFile> content, @RequestParam("description") String description, @RequestParam("hashtagIds") List<Integer> hashtagIds, @RequestParam("userId") Long userId) throws PostCustomException {
        CreatePostRequestModel requestModel = new CreatePostRequestModel(content, description, hashtagIds, userId);
        return this.inputBoundary.create(requestModel);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<GetUserPostsResponseModel>> getPostsByUserId(@PathVariable("id") long userId) throws PostCustomException {
        List<GetUserPostsResponseModel> userPosts = this.inputBoundary.findByUserId(userId);

        if (userPosts == null || userPosts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userPosts);
    }

}
