package com.jarno.cr8ive.persistance.converter.impl;

import com.jarno.cr8ive.domain.Content;
import com.jarno.cr8ive.domain.Post;
import com.jarno.cr8ive.persistance.converter.PostConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.ContentJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.HashtagJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostConverterImpl implements PostConverter {

    @Override
    public List<Post> toPosts(List<PostJpaMapper> jpaMappers) {
        if (jpaMappers == null) {
            return Collections.emptyList();
        }

        return jpaMappers.stream()
                .map(jpaMapper -> Post.builder()
                        .id(jpaMapper.getId())
                        .content(mapContentJpaMappers(jpaMapper.getPostContents()))
                        .description(jpaMapper.getDescription())
                        .creationDate(jpaMapper.getCreationDate())
                        .shareCount(jpaMapper.getShareCount())
                        .hashtagIds(mapHashtagJpaMappers(jpaMapper.getHashtags()))
                        .userId(jpaMapper.getUserId())
                        .build())
                .toList();
    }


    public Post toPost(PostJpaMapper postJpaMapper){
        return Post.builder()
                .id(postJpaMapper.getId())
                .content(mapContentJpaMappers(postJpaMapper.getPostContents()))
                .description(postJpaMapper.getDescription())
                .creationDate(postJpaMapper.getCreationDate())
                .shareCount(postJpaMapper.getShareCount())
                .hashtagIds(mapHashtagJpaMappers(postJpaMapper.getHashtags()))
                .userId(postJpaMapper.getUserId())
                .build();
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
                .toList();
    }

    private static List<Integer> mapHashtagJpaMappers(Set<HashtagJpaMapper> hashtagJpaMappers) {
        if (hashtagJpaMappers == null) {
            return Collections.emptyList();
        }

        return hashtagJpaMappers.stream()
                .map(HashtagJpaMapper::getId)
                .toList();
    }

    @Override
    public PostJpaMapper toPostJpaMapper(Post post, Set<HashtagJpaMapper> hashtagJpaMappers) {
        List<ContentJpaMapper> contentJpaMappers = mapContent(post.getContent());
        PostJpaMapper postJpaMapper = PostJpaMapper.builder()
                .postContents(contentJpaMappers)
                .description(post.getDescription())
                .creationDate(post.getCreationDate())
                .shareCount(post.getShareCount())
                .hashtags(hashtagJpaMappers)
                .userId(post.getUserId())
                .build();

        for (ContentJpaMapper contentJpaMapper : contentJpaMappers) {
            contentJpaMapper.setPost(postJpaMapper);
        }
        return postJpaMapper;
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

    @Override
    public Optional<Post> toSingleOptionalPost(Optional<PostJpaMapper> jpaMapperOptional) {
        return jpaMapperOptional.map(jpaMapper -> Post.builder()
                .id(jpaMapper.getId())
                .content(mapContentJpaMappers(jpaMapper.getPostContents()))
                .description(jpaMapper.getDescription())
                .creationDate(jpaMapper.getCreationDate())
                .shareCount(jpaMapper.getShareCount())
                .hashtagIds(mapHashtagJpaMappers(jpaMapper.getHashtags()))
                .userId(jpaMapper.getUserId())
                .build());
    }

    public PostJpaMapper updateDescription(PostJpaMapper postEntity, String description){
        return PostJpaMapper.builder()
                .id(postEntity.getId())
                .postContents(postEntity.getPostContents())
                .description(description)
                .creationDate(postEntity.getCreationDate())
                .likes(postEntity.getLikes())
                .shareCount(postEntity.getShareCount())
                .hashtags(postEntity.getHashtags())
                .userId(postEntity.getUserId())
                .seenByUsers(postEntity.getSeenByUsers())
                .build();

    }
}
