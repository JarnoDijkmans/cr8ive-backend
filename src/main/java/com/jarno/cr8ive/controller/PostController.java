package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.boundaries.services.IAuthService;
import com.jarno.cr8ive.business.boundaries.services.IPostService;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.post.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.request.post.UpdateDescriptionRequestModel;
import com.jarno.cr8ive.business.model.response.post.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetPostByPostIdResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetUserPostsResponseModel;
import com.jarno.cr8ive.business.model.response.post.UpdateDescriptionResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {
    private final IPostService service;
    private final IAuthService authService;

    @Autowired
    public PostController(IPostService service, IAuthService authService) {
        this.service = service;
        this.authService = authService;
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

    @GetMapping("/postId/{postId}")
    public GetPostByPostIdResponseModel getPostByPostId(@PathVariable("postId")long postId) throws PostCustomException{
        return this.service.findByPostId(postId);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId, @RequestHeader("Authorization") String authorizationHeader) throws PostCustomException {
        String token = extractTokenFromAuthorizationHeader(authorizationHeader);

        long userId = authService.extractUserIdFromToken(token);
        Set<String> userRoles = authService.extractRolesFromToken(token);

        if (userRoles.contains("MODERATOR")) {
            service.deleteUserPost(postId);
            return ResponseEntity.ok("Post deleted successfully by maintainer.");
        } else {
            if (service.userOwnsPost(postId, userId)) {
                service.deleteUserPost(postId);
                return ResponseEntity.ok("Post deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized to delete this post.");
            }
        }
    }

    @PostMapping("/update/description")
    public ResponseEntity<Object> updateDescription(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UpdateDescriptionRequestModel requestModel) {
        try {
            String token = extractTokenFromAuthorizationHeader(authorizationHeader);
            long userId = authService.extractUserIdFromToken(token);

            if (service.userOwnsPost(requestModel.getPostId(), userId)) {
                UpdateDescriptionResponseModel updatedDescription = this.service.updateDescription(requestModel.getPostId(), requestModel.getDescription());
                return ResponseEntity.ok(updatedDescription);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User doesn't own this post");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during the update");
        }
    }

    @GetMapping("/api/latestPost/{currentPage}")
    public GetUserPostsResponseModel getPostLatestPostsForUser(@PathVariable("currentPage") int currentPage) throws PostCustomException {
        return this.service.getLatestPost(currentPage);
    }

    @GetMapping("api/trending/{currentPage}")
    public GetUserPostsResponseModel getTrendingPosts(@PathVariable("currentPage") int currentPage) throws PostCustomException{
        return this.service.getTrendingPostsLastWeek(currentPage);
    }

    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader.replaceFirst("Bearer ", "").trim();
    }
}
