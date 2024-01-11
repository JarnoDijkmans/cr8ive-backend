package com.jarno.cr8ive.business.model.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateDescriptionResponseModel {
    private String description;
}
