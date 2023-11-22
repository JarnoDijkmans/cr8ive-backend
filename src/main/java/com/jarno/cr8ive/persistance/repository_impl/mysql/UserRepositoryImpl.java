package com.jarno.cr8ive.persistance.repository_impl.mysql;

import com.jarno.cr8ive.business.boundaries.repository.IUserRepository;
import com.jarno.cr8ive.domain.user.IUser;
import com.jarno.cr8ive.persistance.converter.UserConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;
import com.jarno.cr8ive.persistance.repository_jpa.JpaUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements IUserRepository {
    private JpaUserRepository repository;
    private final UserConverter userConverter;


    @Override
    public long save (IUser user){
        UserJpaMapper userJpaMapper = userConverter.toUserJpaMapper(user);
        userJpaMapper.getUserRoles().forEach(roles -> roles.setUser(userJpaMapper));
        this.repository.save(userJpaMapper);

        return userJpaMapper.getId();
    }

    @Override
    public boolean existsById(String id) {return repository.existsById(id);}

    @Override
    public List<IUser> getUsersByName(String name){
        List<UserJpaMapper> users = repository.findUsersByName(name);
        return userConverter.toUserList(users);
    }

    @Override
    public IUser findUserByEmailAddress (String emailAddress){
        UserJpaMapper userJpa = repository.findUserByEmailAddress(emailAddress);
        return userConverter.toUser(userJpa);
    }

    @Override
    public IUser findUserById (long id){
        UserJpaMapper userJpa = repository.findUserById(id);
        return userConverter.toUser(userJpa);
    }
}
