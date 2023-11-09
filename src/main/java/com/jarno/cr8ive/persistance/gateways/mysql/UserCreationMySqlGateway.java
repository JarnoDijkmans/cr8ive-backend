package com.jarno.cr8ive.persistance.gateways.mysql;

import com.jarno.cr8ive.persistance.converter.CreateUserConverter;
import com.jarno.cr8ive.persistance.converter.GetUsersConverter;
import com.jarno.cr8ive.persistance.gateways.mapper.UserJpaMapper;
import com.jarno.cr8ive.persistance.repositories.IUserRepository;
import com.jarno.cr8ive.business.boundaries.output.IUserGateway;
import com.jarno.cr8ive.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserCreationMySqlGateway implements IUserGateway {
    private IUserRepository repository;


    @Override
    public long save (User user){
        UserJpaMapper userJpaMapper = CreateUserConverter.toUserJpaMapper(user);
        this.repository.save(userJpaMapper);
        return userJpaMapper.getId();
    }

    @Override
    public boolean existsById(String id) {return repository.existsById(id);}

    @Override
    public List<User> getUsersByName(String name){
        List <UserJpaMapper> users = repository.findUsersByName(name);
        return GetUsersConverter.toUser(users);
    }
}
