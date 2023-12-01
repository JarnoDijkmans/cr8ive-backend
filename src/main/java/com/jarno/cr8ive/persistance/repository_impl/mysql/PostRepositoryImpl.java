package com.jarno.cr8ive.persistance.repository_impl.mysql;

import com.jarno.cr8ive.business.boundaries.repository.IPostRepository;
import com.jarno.cr8ive.domain.Post;
import com.jarno.cr8ive.persistance.converter.CreatePostConverter;
import com.jarno.cr8ive.persistance.converter.GetPostByUserIdConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.HashtagJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;
import com.jarno.cr8ive.persistance.repository_jpa.JpaHashtagRepository;
import com.jarno.cr8ive.persistance.repository_jpa.JpaPostRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@NoArgsConstructor
@Repository
public class PostRepositoryImpl implements IPostRepository {
    private JpaPostRepository repository;
    private JpaHashtagRepository hashtagsRepository;
    @Autowired
    public PostRepositoryImpl(JpaPostRepository repository, JpaHashtagRepository hashtagRepository) {
        this.repository = repository;
        this.hashtagsRepository = hashtagRepository;
    }
    @Transactional
    public long save(Post post) {
        Set<HashtagJpaMapper> hashtagJpaMappers = post.getHashtagIds()
                .stream()
                .map(id -> hashtagsRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        PostJpaMapper postJpaMapper = CreatePostConverter.toPostJpaMapper(post, hashtagJpaMappers);
        return repository.save(postJpaMapper).getId();
    }

    @Transactional
    public List<Post> findByUserId(long userId) {
        List<PostJpaMapper> postsJpa = repository.findByUserId(userId);
        return GetPostByUserIdConverter.toPosts(postsJpa);
    }

    @Override
    public boolean existsById(String id) {return repository.existsById(id);}
}






//    private List<Hashtags> findHashtagsById(List<Integer> hashtagIds) {
//        return hashtagIds.stream()
//                .map(id -> hashtagsRepository.findById(id))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .map(HashtagConverter::toHashtag)
//                .filter(Objects::nonNull)
//                .toList();
//    }