package com.jarno.cr8ive.adapter.converter;

import com.jarno.cr8ive.adapter.gateways.mapper.HashtagJpaMapper;
import com.jarno.cr8ive.domain.Hashtags;

public class HashtagConverter {
    public static Hashtags toHashtag(HashtagJpaMapper hashtagJpaMapper){
        return Hashtags.builder()
                .id(hashtagJpaMapper.getId())
                .name(hashtagJpaMapper.getName())
                .build();
    }
}
