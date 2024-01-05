package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.ILikeRepository;
import com.jarno.cr8ive.business.boundaries.repository.IPostRepository;
import com.jarno.cr8ive.business.boundaries.services.ILikeService;
import com.jarno.cr8ive.business.model.request.like.LikeRequestModel;
import com.jarno.cr8ive.business.model.response.like.UpdateLikeCounterResponseModel;
import com.jarno.cr8ive.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LikeService implements ILikeService {
    private IPostRepository repo;
    private ILikeRepository likeRepository;
    public void getLikesForPosts(List<Post> posts) {
        for (Post post : posts){
            long likes = repo.getLikeCount(post.getId());
            post.setLikes(likes);
        }
    }

    public void getLikesForOptionalPost(Optional<Post> postOptional) {
        postOptional.ifPresent(post -> {
            long likes = repo.getLikeCount(post.getId());
            post.setLikes(likes);
        });
    }

    @Override
    public UpdateLikeCounterResponseModel update(LikeRequestModel requestModel){
        try{
            //Dislike or like a post
            likeRepository.toggleLike(requestModel.getPostId(), requestModel.getUserId(), requestModel.isLiked());
            //receive the new count of likes
            Optional<Post> postOptional = repo.findByPostId(requestModel.getPostId());
            getLikesForOptionalPost(postOptional);
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                return new UpdateLikeCounterResponseModel(post.getLikes());
            } else {
                throw new NoSuchElementException("Post not found");
            }
        } catch (Exception e){
            throw e;
        }
    }

    @Override
    public boolean validatePostLiked(long userId, long postId) {
        try{
            return likeRepository.ValidateIfPostLiked(userId, postId);
        }
        catch (Exception e){
            throw e;
        }
    }
}
