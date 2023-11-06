package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.input.IPostBoundary;
import com.jarno.cr8ive.business.boundaries.output.IPostGateway;
import com.jarno.cr8ive.business.converter.CreatePostConverter;
import com.jarno.cr8ive.business.converter.GetPostByUserIdConverter;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.GetUserPostsResponseModel;
import com.jarno.cr8ive.domain.Hashtags;
import com.jarno.cr8ive.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class PostService implements IPostBoundary {
    private IPostGateway gateway;
    private StorageService storageService;


    @Override
    public CreatePostResponseModel create (CreatePostRequestModel requestModel) throws PostCustomException{
        Post post = CreatePostConverter.toPost(requestModel);

        if (!post.contentIsValid()) {
           throw new PostCustomException("Content " + post.getContent() + " is not valid");
        }

        long result;

        try {
            result = gateway.save(post);

            if (result != 0) {
                for (MultipartFile file : requestModel.getContent()) {
                    storageService.store(file, result, file.getOriginalFilename());
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
    public List<GetUserPostsResponseModel> findByUserId (long userId) throws PostCustomException{

        List<GetUserPostsResponseModel> responseForUser = new ArrayList<>();

        try {
            List<Post> posts = gateway.findByUserId(userId);

            for (Post post : posts) {
                if (post.getContent() != null) {
                    List<Hashtags> hashtagsForPost = gateway.findHashtagsById(post.getHashtagIds());
                    responseForUser.add(GetPostByUserIdConverter.toResponseModel(post, hashtagsForPost));
                }
            }
            return responseForUser;
        }
        catch (Exception e){
            throw new PostCustomException("Retrieval was unsuccessful");
        }
    }
}
