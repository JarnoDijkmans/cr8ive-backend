package com.jarno.cr8ive.business.boundaries.repository;

import com.jarno.cr8ive.business.exeption.IPostExistsGateway;
import com.jarno.cr8ive.domain.Post;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IPostRepository extends IPostExistsGateway {
    long save(Post post);
    List<Post> findByUserId (long userId);

    Optional<Post> findByPostId (long postId);
    void deletePost (long postId);

    List<Post> findLatestPost (Pageable pageable);

    long getLikeCount(long postId);

    List<Post> getTrendingPostsLastWeek(Date startDate, Date endDate, Pageable pageable);

    String updateDescription(long postId, String description);

    List<Post> findByHashtagId(Pageable pageable, int categoryId);
}
