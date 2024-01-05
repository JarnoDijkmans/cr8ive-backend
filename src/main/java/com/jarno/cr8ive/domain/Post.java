package com.jarno.cr8ive.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private long id;
    private List<Content> content;
    private String description;
    private Date creationDate;
    @Setter
    private long likes;
    private long shareCount;
    private List<Integer> hashtagIds;
    private long userId;


    public boolean contentIsValid(){
        return content != null;
    }
}
