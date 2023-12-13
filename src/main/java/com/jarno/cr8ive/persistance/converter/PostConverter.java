package com.jarno.cr8ive.persistance.converter;

import com.jarno.cr8ive.domain.Post;
import com.jarno.cr8ive.persistance.repository_impl.entity.HashtagJpaMapper;
import com.jarno.cr8ive.persistance.repository_impl.entity.PostJpaMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostConverter {
    PostJpaMapper toPostJpaMapper(Post post, Set<HashtagJpaMapper> hashtagJpaMappers);

    List<Post> toPosts(List<PostJpaMapper> jpaMappers);

    Optional<Post> toSingleOptionalPost(Optional<PostJpaMapper> jpaMapper);
}
