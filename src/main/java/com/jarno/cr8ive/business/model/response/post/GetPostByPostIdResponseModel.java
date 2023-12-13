package com.jarno.cr8ive.business.model.response.post;

import com.jarno.cr8ive.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetPostByPostIdResponseModel {
    private Optional<Post> post;
}
