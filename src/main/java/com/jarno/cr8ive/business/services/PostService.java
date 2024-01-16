package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.repository.IPostRepository;
import com.jarno.cr8ive.business.boundaries.services.IPostService;
import com.jarno.cr8ive.business.converter.CreatePostConverter;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.post.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.post.UpdateDescriptionResponseModel;
import com.jarno.cr8ive.business.model.response.post.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetPostByPostIdResponseModel;
import com.jarno.cr8ive.business.model.response.post.GetUserPostsResponseModel;
import com.jarno.cr8ive.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@AllArgsConstructor
@Service
public class PostService implements IPostService {
    private IPostRepository repo;
    private StorageService storageService;
    private LikeService likeService;


    @Override
    public CreatePostResponseModel create (CreatePostRequestModel requestModel) throws PostCustomException{
        Post post = CreatePostConverter.toPost(requestModel);

        if (!post.contentIsValid()) {
           throw new PostCustomException("Content " + post.getContent() + " is not valid");
        }

        long result;

        try {
            result = repo.save(post);

            if (result != 0) {
                for (MultipartFile file : requestModel.getContent()) {
                    storageService.store(file, result);
                }
            } else {
                throw new PostCustomException("something went wrong with creation");
            }
        } catch (Exception e) {
            throw new PostCustomException("save was unsuccessful");
        }

        return new CreatePostResponseModel(result);
    }

    @Override
    public GetUserPostsResponseModel findByUserId (long userId) throws PostCustomException{
        try {
            List <Post> posts = repo.findByUserId(userId);
            likeService.getLikesForPosts(posts);
            return new GetUserPostsResponseModel(posts);
        }
        catch (Exception e){
            throw new PostCustomException("Retrieval of user was unsuccessful");
        }
    }

    @Override
    public void deleteUserPost(long postId) throws PostCustomException {
        try {
            repo.deletePost(postId);
            storageService.deletePost(postId);
        } catch (Exception e) {
            throw new PostCustomException("Something went wrong, Please try again.");
        }
    }

    @Override
    public GetPostByPostIdResponseModel findByPostId(long postId) throws PostCustomException{
        try {
            Optional<Post> postOptional = repo.findByPostId(postId);
            likeService.getLikesForOptionalPost(postOptional);
            return new GetPostByPostIdResponseModel(postOptional);
        }
        catch (Exception e){
            throw new PostCustomException("Retrieval was unsuccessful");
        }
    }

    @Override
    public boolean userOwnsPost (long postId, long userId){
        Optional<Post> postOptional = repo.findByPostId(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return post.getUserId() == userId;
        }

        return false;
    }

    @Override
    public GetUserPostsResponseModel getLatestPost(int currentPage) throws PostCustomException{
        try {
            Pageable pageable = PageRequest.of(currentPage, 5);

            List<Post> posts = repo.findLatestPost(pageable);
            likeService.getLikesForPosts(posts);
            return new GetUserPostsResponseModel(posts);

        }catch (Exception e){
            throw new PostCustomException("Retrieval was unsuccessful");
        }
    }

    @Override
    public GetUserPostsResponseModel getTrendingPostsLastWeek(int currentPage) throws PostCustomException{
        try{
            Calendar calendar = Calendar.getInstance();
            Date endDate = calendar.getTime();

            calendar.add(Calendar.DAY_OF_YEAR, -7);
            Date startDate = calendar.getTime();

            Pageable pageable = PageRequest.of(currentPage, 5);

            List<Post> posts = repo.getTrendingPostsLastWeek(startDate, endDate, pageable);
            if (!posts.isEmpty()) {
                likeService.getLikesForPosts(posts);
            }
            return new GetUserPostsResponseModel(posts);
        }catch (Exception e) {
            throw new PostCustomException("Retrieval trending posts was unsuccessful");
        }
    }

    @Override
    public UpdateDescriptionResponseModel updateDescription(long postId, String description) throws PostCustomException{
        try{
            String newDescription = repo.updateDescription(postId, description);
            return new UpdateDescriptionResponseModel(newDescription);

        }catch (Exception e) {
            throw new PostCustomException("Something went wrong during updating description.");
        }
    }
}
