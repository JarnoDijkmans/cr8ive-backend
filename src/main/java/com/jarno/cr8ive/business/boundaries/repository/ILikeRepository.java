package com.jarno.cr8ive.business.boundaries.repository;

public interface ILikeRepository {
    void toggleLike(long postId, long userId, Boolean like);

    boolean ValidateIfPostLiked(long userId, long postId);
}
