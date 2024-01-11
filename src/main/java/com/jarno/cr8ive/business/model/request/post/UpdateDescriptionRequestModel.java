package com.jarno.cr8ive.business.model.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter

public class UpdateDescriptionRequestModel {
    long postId;
    String description;
}
