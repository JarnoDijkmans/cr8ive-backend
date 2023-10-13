package com.jarno.cr8ive.controller;

import com.jarno.cr8ive.business.ServiceImpl.PostService;
import com.jarno.cr8ive.business.ServiceImpl.StorageService;
import com.jarno.cr8ive.controller.Converters.PostConverter;
import com.jarno.cr8ive.controller.request.post.CreatePostRequest;
import com.jarno.cr8ive.controller.response.post.CreatePostResponse;
import com.jarno.cr8ive.controller.response.post.GetUserPostsResponse;
import com.jarno.cr8ive.domain.Post;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost( @RequestParam("content") List<MultipartFile> content, @RequestParam("description") String description, @RequestParam("hashtagIds") List<Integer> hashtagIds, @RequestParam("userId") Long userId) {
        CreatePostRequest request = PostConverter.toCreatePostRequest(content, description, hashtagIds, userId);
        CreatePostResponse response = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserPostsResponse> getPostsByUserId(@PathVariable("id") long userId) {
        List<Post> userPosts = postService.getUserPosts(userId);
        if (userPosts == null || userPosts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        GetUserPostsResponse response = new GetUserPostsResponse(userPosts);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePostByPostId(@PathVariable int postId) {
        postService.deletePostByPostId(postId);
        return ResponseEntity.noContent().build();
    }
}
