package com.jarno.cr8ive.business.services;

import com.jarno.cr8ive.business.boundaries.input.register.IPostRegisterBoundary;
import com.jarno.cr8ive.business.boundaries.output.register.IPostRegisterGateway;
import com.jarno.cr8ive.business.converter.CreatePostConverter;
import com.jarno.cr8ive.business.converter.GetPostByUserIdConverter;
import com.jarno.cr8ive.business.exeption.PostCustomException;
import com.jarno.cr8ive.business.model.request.CreatePostRequestModel;
import com.jarno.cr8ive.business.model.response.CreatePostResponseModel;
import com.jarno.cr8ive.business.model.response.GetUserPostsResponseModel;
import com.jarno.cr8ive.business.presenter.IPostPresenter;
import com.jarno.cr8ive.domain.Hashtags;
import com.jarno.cr8ive.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class PostService implements IPostRegisterBoundary {
    IPostPresenter presenter;
    IPostRegisterGateway gateway;
    StorageService storageService;


    @Override
    public CreatePostResponseModel create (CreatePostRequestModel requestModel) throws PostCustomException{
        Post post = CreatePostConverter.toPost(requestModel);

        if (!post.contentIsValid()) {
            return presenter.prepareFailView(new PostCustomException("Content " + post.getContent() + " is not valid"));
        }

        long result = 0;

        try {
            result = gateway.save(post);

            if (result != 0) {
                for (MultipartFile file : requestModel.getContent()) {
                    storageService.store(file, result, file.getOriginalFilename());
                }
            } else {
                return presenter.prepareFailView(new PostCustomException("Something went wrong Id: " + result));
            }
        } catch (Exception e) {
            return presenter.prepareFailView(new PostCustomException("Save was unsuccessful"));
        }

        CreatePostResponseModel responseModel = new CreatePostResponseModel(result);

        return presenter.prepareSuccessView(responseModel);
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
            return (presenter.prepareFailViewForFindByUserId(new PostCustomException("Retrieval was unsuccessful")));
        }
    }
}
