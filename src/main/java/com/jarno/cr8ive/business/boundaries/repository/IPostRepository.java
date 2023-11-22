package com.jarno.cr8ive.business.boundaries.repository;

import com.jarno.cr8ive.business.exeption.IPostExistsGateway;
import com.jarno.cr8ive.domain.Hashtags;
import com.jarno.cr8ive.domain.Post;

import java.util.List;

public interface IPostRepository extends IPostExistsGateway {
    long save(Post post);
    List<Post> findByUserId (long userId);
    List <Hashtags> findHashtagsById(List <Integer> hashtagIds);
}
