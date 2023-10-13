package com.jarno.cr8ive.controller.Converters;

import com.jarno.cr8ive.controller.request.post.CreatePostRequest;
import com.jarno.cr8ive.controller.response.post.CreatePostResponse;
import com.jarno.cr8ive.domain.Post;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostConverter {

    public static CreatePostRequest toCreatePostRequest (List<MultipartFile> content, String description, List<Integer> hashtagIds, Long userId){
        return CreatePostRequest.builder()
                .content(content)
                .description(description)
                .creationDate(new Date())
                .likes(0)
                .shareCount(0)
                .hashtagIds(hashtagIds)
                .userId(userId)
                .build();
    }
}
