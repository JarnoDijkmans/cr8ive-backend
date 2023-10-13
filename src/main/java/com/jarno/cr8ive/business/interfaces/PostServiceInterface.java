package com.jarno.cr8ive.business.interfaces;

import com.jarno.cr8ive.controller.request.post.CreatePostRequest;
import com.jarno.cr8ive.controller.response.post.CreatePostResponse;
import com.jarno.cr8ive.domain.Post;

import java.util.List;

public interface PostServiceInterface {
    CreatePostResponse createPost (CreatePostRequest request);
    void deletePostByPostId (long postId);
    List<Post> getUserPosts(long userId);
}
