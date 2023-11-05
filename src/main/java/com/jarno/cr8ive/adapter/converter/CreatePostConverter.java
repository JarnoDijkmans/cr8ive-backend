package com.jarno.cr8ive.adapter.converter;

import com.jarno.cr8ive.adapter.gateways.mapper.ContentJpaMapper;
import com.jarno.cr8ive.adapter.gateways.mapper.HashtagJpaMapper;
import com.jarno.cr8ive.adapter.gateways.mapper.PostJpaMapper;
import com.jarno.cr8ive.domain.Content;
import com.jarno.cr8ive.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CreatePostConverter {

    private CreatePostConverter(){}
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

    private static List<ContentJpaMapper> mapContent(List<Content> contentUrls) {
        List<ContentJpaMapper> contentJpaMappers = new ArrayList<>();
        if (contentUrls != null) {
            for (Content content : contentUrls) {
                contentJpaMappers.add(createContentJpaMapper(content.getUrl(), content.getType()));
            }
        }
        return contentJpaMappers;
    }

    private static ContentJpaMapper createContentJpaMapper(String fileUrl, String type) {
        ContentJpaMapper contentJpaMapper = new ContentJpaMapper();
        contentJpaMapper.setFileUrl(fileUrl);
        contentJpaMapper.setExtension(type);
        return contentJpaMapper;
    }
}

