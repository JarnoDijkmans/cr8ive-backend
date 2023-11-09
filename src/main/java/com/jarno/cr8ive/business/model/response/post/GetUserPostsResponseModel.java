package com.jarno.cr8ive.business.model.response.post;

import com.jarno.cr8ive.domain.Content;
import com.jarno.cr8ive.domain.Hashtags;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPostsResponseModel {
    private long id;
    private List<Content> content;
    private String description;
    private Date creationDate;
    private long likes;
    private long shareCount;
    //private long comments;
    private List<Hashtags> hashtags;
    private long userId;
}
