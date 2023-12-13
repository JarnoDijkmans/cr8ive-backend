package com.jarno.cr8ive.persistance.repository_impl.mysql;

import com.jarno.cr8ive.business.boundaries.repository.IPostRepository;
import com.jarno.cr8ive.domain.Post;
import com.jarno.cr8ive.persistance.converter.PostConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.HashtagJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;
import com.jarno.cr8ive.persistance.repository_jpa.JpaHashtagRepository;
import com.jarno.cr8ive.persistance.repository_jpa.JpaPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@Repository
public class PostRepositoryImpl implements IPostRepository {
    private final JpaPostRepository repository;
    private final JpaHashtagRepository hashtagsRepository;
    private final PostConverter converter;
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
    public boolean existsById(String id) {return repository.existsById(id);}

    @Override
    public Optional<Post> findByPostId (long postId){
        Optional<PostJpaMapper> optionalPostJpaMapper = repository.findPostById(postId);
        return converter.toSingleOptionalPost(optionalPostJpaMapper);
    }
    @Override
    @Transactional
    public void deletePost (long postId){
        repository.deleteById(postId);
    }

    private Set<HashtagJpaMapper> findHashtagsByIds(Post post){
        Set<HashtagJpaMapper> hashtagJpaMappers = new HashSet<>();

        for (Integer id : post.getHashtagIds()){
            hashtagsRepository.findById(id)
                    .ifPresent(hashtagJpaMappers::add);
        }
        return hashtagJpaMappers;
    }
}



