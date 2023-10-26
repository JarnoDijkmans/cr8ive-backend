package com.jarno.cr8ive.adapter.gateways.mySql;

import com.jarno.cr8ive.adapter.converter.CreateUserConverter;
import com.jarno.cr8ive.adapter.gateways.mapper.UserJpaMapper;
import com.jarno.cr8ive.adapter.repositories.IUserRepository;
import com.jarno.cr8ive.business.boundaries.output.register.IUserRegisterGateway;
import com.jarno.cr8ive.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserCreationMySqlGateway implements IUserRegisterGateway {
    private IUserRepository repository;


    @Override
    public long save (User user){
        UserJpaMapper userJpaMapper = CreateUserConverter.toUserJpaMapper(user);
        this.repository.save(userJpaMapper);
        return userJpaMapper.getId();
    }

    @Override
    public boolean existsById(String id) {return repository.existsById(id);}
}
