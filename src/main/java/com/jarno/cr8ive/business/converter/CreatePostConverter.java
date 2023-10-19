package com.jarno.cr8ive.business.converter;

import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.domain.Post;

public class CreatePostConverter {
    public static Post toPost (CreatePostRequestModel requestModel){

        return Post.builder()
                .content(requestModel.getContent())
                .description(requestModel.getDescription())
                .creationDate(requestModel.getCreationDate())
                .likes(requestModel.getLikes())
                .shareCount(requestModel.getShareCount())
                .hashtagIds(requestModel.getHashtagIds())
                .userId(requestModel.getUserId())
                .build();
    }
}
