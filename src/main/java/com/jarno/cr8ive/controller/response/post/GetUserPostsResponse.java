package com.jarno.cr8ive.controller.response.post;

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
public class GetUserPostsResponse {
    private List<Post> posts;
}
