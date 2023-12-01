package com.jarno.cr8ive.business.converter;

import com.jarno.cr8ive.business.model.request.post.CreatePostRequestModel;
import com.jarno.cr8ive.domain.Content;
import com.jarno.cr8ive.domain.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreatePostConverter {

    private CreatePostConverter (){}
    public static Post toPost(CreatePostRequestModel requestModel) {
        List<Content> files = new ArrayList<>();

        if (requestModel.getContent() != null) {
            for (MultipartFile file : requestModel.getContent()) {
                files.add(Content.builder()
                        .url(file.getOriginalFilename())
                        .type(file.getContentType())
                        .build());
            }
        }

        return Post.builder()
                .content(files)
                .description(requestModel.getDescription())
                .creationDate(new Date())
                .likes(0)
                .shareCount(0)
                .hashtagIds(requestModel.getHashtagIds())
                .userId(requestModel.getUserId())
                .build();
    }
}
