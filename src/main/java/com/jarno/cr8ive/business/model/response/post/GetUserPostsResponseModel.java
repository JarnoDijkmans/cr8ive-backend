package com.jarno.cr8ive.business.model.response.post;

import com.jarno.cr8ive.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserPostsResponseModel {
    private List<Post> post;
}
