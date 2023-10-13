package com.jarno.cr8ive.controller.request.post;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {
    @NotBlank
    private List<MultipartFile> content;
    private String description;
    private Date creationDate;
    private long likes;
    private long shareCount;
    private List<Integer> hashtagIds;
    private long userId;
}
