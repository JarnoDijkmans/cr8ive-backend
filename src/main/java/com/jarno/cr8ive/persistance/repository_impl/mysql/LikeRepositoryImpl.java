package com.jarno.cr8ive.persistance.repository_impl.mysql;

import com.jarno.cr8ive.business.boundaries.repository.ILikeRepository;
import com.jarno.cr8ive.persistance.repository_impl.entity.LikeJpaMapper;
import com.jarno.cr8ive.persistance.repository_jpa.JpaLikeRepository;
import com.jarno.cr8ive.persistance.repository_jpa.JpaPostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class LikeRepositoryImpl implements ILikeRepository {

    JpaLikeRepository repository;
    JpaPostRepository postRepository;

    @Autowired
    public LikeRepositoryImpl(JpaLikeRepository repository, JpaPostRepository postRepository){
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void toggleLike(long postId, long userId, Boolean like) {
        Optional<LikeJpaMapper> optionalLike = repository.findByPostIdAndUserId(postId, userId);

        if (Boolean.TRUE.equals(like) && optionalLike.isEmpty()) {
            LikeJpaMapper likeJpaMapper = LikeJpaMapper.builder()
                    .post(postRepository.getReferenceById(postId))
                    .userId(userId)
                    .likeDate(new Date())
                    .build();

            repository.save(likeJpaMapper);
        } else if (Boolean.FALSE.equals(like) && optionalLike.isPresent()) {
            repository.delete(optionalLike.get());
        }
    }

    @Override
    public boolean ValidateIfPostLiked(long userId, long postId) {
        return repository.existsByPostIdAndUserId(postId, userId);
    }
}
