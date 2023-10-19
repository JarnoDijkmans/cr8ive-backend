package com.jarno.cr8ive.adapter.gateways.mySql;

import com.jarno.cr8ive.adapter.converter.CreatePostConverter;
import com.jarno.cr8ive.adapter.gateways.mapper.PostJpaMapper;
import com.jarno.cr8ive.adapter.repositories.IPostRepository;
import com.jarno.cr8ive.business.boundaries.output.register.IPostRegisterGateway;
import com.jarno.cr8ive.domain.Post;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostCreationMySqlGateway implements IPostRegisterGateway {
    IPostRepository _repository;

    @Override
    public void save (Post post){
        PostJpaMapper postJpaMapper = CreatePostConverter.toPostJpaMapper(post);
        this._repository.save(postJpaMapper);
    }

    @Override
    public boolean existsById(String id) {return _repository.existsById(id);}
}
