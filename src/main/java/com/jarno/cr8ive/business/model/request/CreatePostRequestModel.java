package com.jarno.cr8ive.business.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CreatePostRequestModel {
    private List<MultipartFile> content;
    private String description;
    private Date creationDate;
    private long likes;
    private long shareCount;
    private int commandId;
    private List<Integer> hashtagIds;
    private long userId;
}
