package com.jarno.cr8ive.business.converters;

import com.jarno.cr8ive.controller.request.post.CreatePostRequest;
import com.jarno.cr8ive.controller.response.post.CreatePostResponse;
import com.jarno.cr8ive.domain.Post;

public class CreatePostConverter {

    public static Post toPost(CreatePostRequest request) {
        return Post.builder()
                .content(request.getContent())
                .description(request.getDescription())
                .creationDate(request.getCreationDate())
                .likes(request.getLikes())
                .shareCount(request.getShareCount())
                .hashtagIds(request.getHashtagIds())
                .userId(request.getUserId())
                .build();
    }



    public static CreatePostResponse toResponse(Post post){
        return CreatePostResponse.builder()
                .id(post.getId())
                .build();
    }
}
