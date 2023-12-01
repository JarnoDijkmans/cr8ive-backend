package com.jarno.cr8ive.persistance.converter;

import com.jarno.cr8ive.persistance.repository_impl.entity.HashtagJpaMapper;
import com.jarno.cr8ive.domain.Hashtags;

public class HashtagConverter {
    private HashtagConverter(){}
    public static Hashtags toHashtag(HashtagJpaMapper hashtagJpaMapper){
        return Hashtags.builder()
                .id(hashtagJpaMapper.getId())
                .name(hashtagJpaMapper.getName())
                .build();
    }
}
