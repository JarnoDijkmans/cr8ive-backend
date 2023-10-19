package com.jarno.cr8ive.adapter.converter;

import com.jarno.cr8ive.adapter.gateways.mapper.ContentJpaMapper;
import com.jarno.cr8ive.adapter.gateways.mapper.HashtagJpaMapper;
import com.jarno.cr8ive.adapter.gateways.mapper.PostJpaMapper;
import com.jarno.cr8ive.domain.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


public class CreatePostConverter {
    public static PostJpaMapper toPostJpaMapper(Post post) {

        List<ContentJpaMapper> contentJpaMappers = new ArrayList<>();
        if (post.getContent() != null) {
            for (MultipartFile file : post.getContent()) {
                ContentJpaMapper contentJpaMapper = new ContentJpaMapper();
                contentJpaMapper.setFileUrl(file.getOriginalFilename());
                contentJpaMappers.add(contentJpaMapper);
            }
        }

        List<HashtagJpaMapper> hashtagJpaMappers = new ArrayList<>();
        if (post.getHashtagIds() != null) {
            for (Integer hashtagId : post.getHashtagIds()) {
                HashtagJpaMapper hashtagJpaMapper = new HashtagJpaMapper();
                hashtagJpaMapper.setId(hashtagId);
                hashtagJpaMappers.add(hashtagJpaMapper);
            }
        }


        return PostJpaMapper.builder()
                .content(contentJpaMappers)
                .description(post.getDescription())
                .creationDate(post.getCreationDate())
                .likes(post.getLikes())
                .shareCount(post.getShareCount())
                .commandId(post.getCommandId())
                .hashtags(hashtagJpaMappers)
                .user(post.getUserId())
                .build();


    }
}

