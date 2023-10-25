package com.jarno.cr8ive.adapter.converter;

import com.jarno.cr8ive.adapter.gateways.mapper.ContentJpaMapper;
import com.jarno.cr8ive.adapter.gateways.mapper.HashtagJpaMapper;
import com.jarno.cr8ive.adapter.gateways.mapper.PostJpaMapper;
import com.jarno.cr8ive.domain.Post;

import java.util.*;


public class CreatePostConverter {
    public static PostJpaMapper toPostJpaMapper(Post post, Set<HashtagJpaMapper> hashtagJpaMappers) {
        List<ContentJpaMapper> contentJpaMappers = mapContent(post.getContent());
        return PostJpaMapper.builder()
                .postContents(contentJpaMappers)
                .description(post.getDescription())
                .creationDate(post.getCreationDate())
                .likes(post.getLikes())
                .shareCount(post.getShareCount())
                .hashtags(hashtagJpaMappers)
                .userId(post.getUserId())
                .build();
    }

    private static List<ContentJpaMapper> mapContent(List<String> contentUrls) {
        List<ContentJpaMapper> contentJpaMappers = new ArrayList<>();
        if (contentUrls != null) {
            for (String fileUrl : contentUrls) {
                contentJpaMappers.add(createContentJpaMapper(fileUrl));
            }
        }
        return contentJpaMappers;
    }

    private static ContentJpaMapper createContentJpaMapper(String fileUrl) {
        ContentJpaMapper contentJpaMapper = new ContentJpaMapper();
        contentJpaMapper.setFileUrl(fileUrl);
        return contentJpaMapper;
    }
}

