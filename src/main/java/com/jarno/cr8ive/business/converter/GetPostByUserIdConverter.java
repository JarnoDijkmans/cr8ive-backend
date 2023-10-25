package com.jarno.cr8ive.business.converter;

import com.jarno.cr8ive.business.model.response.GetUserPostsResponseModel;
import com.jarno.cr8ive.domain.Hashtags;
import com.jarno.cr8ive.domain.Post;

import java.util.List;



public class GetPostByUserIdConverter {
    public static GetUserPostsResponseModel toResponseModel (Post post, List<Hashtags> hashtagsForPost){

        return GetUserPostsResponseModel.builder()
                .id(post.getId())
                .content(post.getContent())
                .description(post.getDescription())
                .creationDate(post.getCreationDate())
                .likes(post.getLikes())
                .shareCount(post.getShareCount())
                .hashtags(hashtagsForPost)
                .userId(post.getUserId())
                .build();
    }
}
