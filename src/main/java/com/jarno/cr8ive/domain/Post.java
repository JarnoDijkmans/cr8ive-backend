package com.jarno.cr8ive.domain;

import lombok.*;
import org.apache.catalina.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private long id;
    private List<MultipartFile> content;
    private String description;
    private Date creationDate;
    private long likes;
    private long shareCount;
    private int commandId;
    private List<Integer> hashtagIds;
    private long userId;


    public boolean contentIsValid(){
        return content != null;
    }
}
