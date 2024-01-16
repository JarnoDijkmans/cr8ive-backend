package com.jarno.cr8ive.persistance.repository_impl.mysql;

import com.jarno.cr8ive.business.boundaries.repository.IPostRepository;
import com.jarno.cr8ive.domain.Post;
import com.jarno.cr8ive.persistance.converter.PostConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.HashtagJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;
import com.jarno.cr8ive.persistance.repository_jpa.JpaHashtagRepository;
import com.jarno.cr8ive.persistance.repository_jpa.JpaPostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Repository
public class PostRepositoryImpl implements IPostRepository {
    private final JpaPostRepository repository;
    private final JpaHashtagRepository hashtagsRepository;
    private final PostConverter converter;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PostRepositoryImpl(JpaPostRepository repository, JpaHashtagRepository hashtagRepository, PostConverter converter) {
        this.repository = repository;
        this.hashtagsRepository = hashtagRepository;
        this.converter = converter;
    }
    @Transactional
    public long save(Post post) {
        Set<HashtagJpaMapper> hashtagJpaMappers = findHashtagsByIds(post);
        PostJpaMapper postJpaMapper = converter.toPostJpaMapper(post, hashtagJpaMappers);
        return repository.save(postJpaMapper).getId();
    }

    @Transactional
    public List<Post> findByUserId(long userId) {
        List<PostJpaMapper> postsJpa = repository.findByUserId(userId);
        return converter.toPosts(postsJpa);
    }

    @Override
    public Optional<Post> findByPostId (long postId){
        Optional<PostJpaMapper> optionalPostJpaMapper = repository.findPostById(postId);
        return converter.toSingleOptionalPost(optionalPostJpaMapper);
    }
    @Transactional
    public void deletePost(long postId) {
        PostJpaMapper post = entityManager.find(PostJpaMapper.class, postId);
        if (post != null) {
            post.getLikes().forEach(entityManager::remove);
            post.getPostContents().forEach(entityManager::remove);
            post.getHashtags().clear();
            post.getSeenByUsers().forEach(entityManager::remove);
            entityManager.remove(post);
            entityManager.flush();
        }
    }

    private Set<HashtagJpaMapper> findHashtagsByIds(Post post){
        Set<HashtagJpaMapper> hashtagJpaMappers = new HashSet<>();

        for (Integer id : post.getHashtagIds()){
            hashtagsRepository.findById(id)
                    .ifPresent(hashtagJpaMappers::add);
        }
        return hashtagJpaMappers;
    }

    public long getLikeCount(long postId){
        return repository.findLikeCountByPostId(postId);
    }

    public List<Post> findLatestPost (Pageable pageable){
        List<PostJpaMapper> postJpaMappers = repository.findLatestPosts(pageable);
        return converter.toPosts(postJpaMappers);
    }

    public List<Post> getTrendingPostsLastWeek(Date startDate, Date endDate, Pageable pageable) {
        List<PostJpaMapper> postJpaMappers = repository.findPostsInDateRange(startDate, endDate, pageable);
        return converter.toPosts(postJpaMappers);
    }

    public String updateDescription(long postId, String description) {
        Optional<PostJpaMapper> postOptional = repository.findById(postId);

        return postOptional.map(postEntity -> {
            PostJpaMapper updatedPost = converter.updateDescription(postEntity, description);
            if (updatedPost != null) {
                PostJpaMapper post = repository.save(updatedPost);
                return post.getDescription();
            }
            return null;
        }).orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));
    }
}



