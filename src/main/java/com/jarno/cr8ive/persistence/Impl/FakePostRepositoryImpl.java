package com.jarno.cr8ive.persistence.Impl;

import com.jarno.cr8ive.business.interfaces.PostRepository;
import com.jarno.cr8ive.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FakePostRepositoryImpl implements PostRepository {

    private final List<Post> savedPost;
    private static long NEXT_ID = 1;

    public FakePostRepositoryImpl() {
        this.savedPost = new ArrayList<>();

    }
    @Override
    public boolean alreadyExistsByPostId(long postId) {
        return this.savedPost
                .stream()
                .anyMatch(post -> post.getId() == (postId));
    }

    @Override
    public boolean DoesntExistsByUserId(long userId) {
            return this.savedPost
                .stream()
                .anyMatch(post -> post.getUserId() == (userId));
    }

    @Override
    public Post save(Post post) {
        post.setId(NEXT_ID);
        setNextId(NEXT_ID);
        alreadyExistsByPostId(post.getId());
        this.savedPost.add(post);
        return post;
    }

    public void setNextId(Long id) {
        NEXT_ID++;
    }

    @Override
    public List<Post> findById(long userId) {
        return this.savedPost.stream()
                .filter(post -> post.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePostById(long postId){
        this.savedPost.removeIf(post -> post.getId() == (postId));
    }
}
