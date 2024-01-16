package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.boundaries.services.IAuthService;
import com.jarno.cr8ive.business.boundaries.services.ILikeService;
import com.jarno.cr8ive.business.model.request.like.LikeRequestModel;
import com.jarno.cr8ive.business.model.response.like.UpdateLikeCounterResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@CrossOrigin(origins = "http://localhost:5173")
public class LikeController {
    private final ILikeService service;
    private final IAuthService authService;

    @Autowired
    public LikeController (ILikeService service, IAuthService authService){
        this.service = service;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Object> updateLikeCounter(@RequestHeader("Authorization") String authorizationHeader, @RequestBody LikeRequestModel requestModel) {
        try {
            String token = authService.extractTokenFromAuthorizationHeader(authorizationHeader);
            long userId = authService.extractUserIdFromToken(token);
            UpdateLikeCounterResponseModel responseModel = service.update(requestModel, userId);
            return ResponseEntity.ok(responseModel);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during the update");
        }
    }

    @GetMapping("/{userId}/{postId}")
    public boolean validatePostLikes(@PathVariable ("userId") long userId, @PathVariable("postId") long postId){
        return this.service.validatePostLiked(userId, postId);
    }
}
