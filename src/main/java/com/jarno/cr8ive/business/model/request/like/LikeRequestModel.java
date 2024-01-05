package com.jarno.cr8ive.business.model.request.like;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeRequestModel {
    long postId;
    long userId;
    boolean liked;
}
