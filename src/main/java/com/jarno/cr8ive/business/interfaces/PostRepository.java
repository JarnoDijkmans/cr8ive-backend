package com.jarno.cr8ive.business.interfaces;

import com.jarno.cr8ive.domain.Post;

import java.util.List;


public interface PostRepository {
    boolean alreadyExistsByPostId(long postId);

    boolean DoesntExistsByUserId(long userId);

    Post save(Post post);

    List<Post> findById(long userId);

    void deletePostById(long postId);
}