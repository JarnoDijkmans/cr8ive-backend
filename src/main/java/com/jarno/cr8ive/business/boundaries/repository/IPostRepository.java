package com.jarno.cr8ive.business.boundaries.repository;

import com.jarno.cr8ive.business.exeption.IPostExistsGateway;
import com.jarno.cr8ive.domain.Post;

import java.util.List;
import java.util.Optional;

public interface IPostRepository extends IPostExistsGateway {
    long save(Post post);
    List<Post> findByUserId (long userId);

    Optional<Post> findByPostId (long postId);
    void deletePost (long postId);

    List<Post> findLatestPost (long userId);
}
