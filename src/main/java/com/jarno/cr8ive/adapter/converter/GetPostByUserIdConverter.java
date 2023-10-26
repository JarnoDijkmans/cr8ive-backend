package com.jarno.cr8ive.adapter.converter;

import com.jarno.cr8ive.adapter.gateways.mapper.ContentJpaMapper;
import com.jarno.cr8ive.adapter.gateways.mapper.HashtagJpaMapper;
import com.jarno.cr8ive.adapter.gateways.mapper.PostJpaMapper;
import com.jarno.cr8ive.domain.Content;
import com.jarno.cr8ive.domain.Post;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GetPostByUserIdConverter {

    private GetPostByUserIdConverter(){

    }
    public static List<Post> toPosts(List<PostJpaMapper> jpaMappers) {
        if (jpaMappers == null) {
            return Collections.emptyList();
        }

        return jpaMappers.stream()
                .map(jpaMapper -> Post.builder()
                        .id(jpaMapper.getId())
                        .content(mapContentJpaMappers(jpaMapper.getPostContents()))
                        .description(jpaMapper.getDescription())
                        .creationDate(jpaMapper.getCreationDate())
                        .likes(jpaMapper.getLikes())
                        .shareCount(jpaMapper.getShareCount())
                        .hashtagIds(mapHashtagJpaMappers(jpaMapper.getHashtags()))
                        .userId(jpaMapper.getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    private static List<Content> mapContentJpaMappers(List<ContentJpaMapper> contentJpaMappers) {
        if (contentJpaMappers == null) {
            return Collections.emptyList();
        }

        return contentJpaMappers.stream()
                .map(contentJpaMapper -> Content.builder()
                        .url(contentJpaMapper.getFileUrl())
                        .type(contentJpaMapper.getExtension())
                        .build())
                .collect(Collectors.toList());
    }

    private static List<Integer> mapHashtagJpaMappers(Set<HashtagJpaMapper> hashtagJpaMappers) {
        if (hashtagJpaMappers == null) {
            return Collections.emptyList();
        }

        return hashtagJpaMappers.stream()
                .map(HashtagJpaMapper::getId)
                .collect(Collectors.toList());
    }
}
