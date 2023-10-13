package com.jarno.cr8ive.business.ServiceImpl;

import com.jarno.cr8ive.business.converters.CreatePostConverter;
import com.jarno.cr8ive.business.exception.PostNotFoundException;
import com.jarno.cr8ive.business.exception.UserIdDoesntExistsException;
import com.jarno.cr8ive.business.interfaces.PostServiceInterface;
import com.jarno.cr8ive.business.interfaces.PostRepository;
import com.jarno.cr8ive.controller.request.post.CreatePostRequest;
import com.jarno.cr8ive.controller.response.post.CreatePostResponse;
import com.jarno.cr8ive.domain.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService implements PostServiceInterface {
    private PostRepository postRepository;
    private StorageService storageService;

    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {
        Post post = CreatePostConverter.toPost(request);

        Post savedPost = postRepository.save(post);

        if (savedPost != null) {
            for (MultipartFile file : savedPost.getContent()) {
                storageService.store(file, savedPost.getId(), file.getOriginalFilename());
            }
            return CreatePostConverter.toResponse(savedPost);
        } else {
            return null;
        }
    }

    @Override
    public void deletePostByPostId (long postId){
        try {
            postRepository.deletePostById(postId);
        } catch (Exception e) {
            throw new PostNotFoundException(postId);
        }
    }
    public List<Post> getUserPosts(long userId) {
        if (!postRepository.DoesntExistsByUserId(userId)) {
            throw new UserIdDoesntExistsException();
        }
        List<Post> posts = postRepository.findById(userId);
        return posts;
    }
}
