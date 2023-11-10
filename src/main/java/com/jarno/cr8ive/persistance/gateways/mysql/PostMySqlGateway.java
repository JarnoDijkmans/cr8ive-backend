package com.jarno.cr8ive.persistance.gateways.mysql;

import com.jarno.cr8ive.persistance.converter.CreatePostConverter;
import com.jarno.cr8ive.persistance.converter.GetPostByUserIdConverter;
import com.jarno.cr8ive.persistance.converter.HashtagConverter;
import com.jarno.cr8ive.persistance.gateways.mapper.HashtagJpaMapper;
import com.jarno.cr8ive.persistance.gateways.mapper.PostJpaMapper;
import com.jarno.cr8ive.persistance.repositories.IHashtagRepository;
import com.jarno.cr8ive.persistance.repositories.IPostRepository;
import com.jarno.cr8ive.business.boundaries.output.IPostGateway;
import com.jarno.cr8ive.domain.Hashtags;
import com.jarno.cr8ive.domain.Post;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@NoArgsConstructor
@Repository
public class PostMySqlGateway implements IPostGateway {
    private IPostRepository repository;
    private IHashtagRepository hashtagsRepository;
    @Autowired
    public PostMySqlGateway(IPostRepository repository, IHashtagRepository hashtagRepository) {
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
    public List<Hashtags> findHashtagsById(List<Integer> hashtagIds) {
        return hashtagIds.stream()
                .map(id -> hashtagsRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(HashtagConverter::toHashtag)
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public boolean existsById(String id) {return repository.existsById(id);}

}
