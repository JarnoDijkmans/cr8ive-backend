package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.boundaries.services.ILikeService;
import com.jarno.cr8ive.business.model.request.like.LikeRequestModel;
import com.jarno.cr8ive.business.model.response.like.UpdateLikeCounterResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "http://localhost:5173")
public class LikeController {
    private final ILikeService service;

    @Autowired
    public LikeController (ILikeService service){
        this.service = service;
    }

    @PostMapping
    public UpdateLikeCounterResponseModel updateLikeCounter(@RequestBody LikeRequestModel requestModel) {
        return this.service.update(requestModel);
    }

    @GetMapping("/{userId}/{postId}")
    public boolean validatePostLikes(@PathVariable ("userId") long userId, @PathVariable("postId") long postId){
        return this.service.validatePostLiked(userId, postId);
    }
}
