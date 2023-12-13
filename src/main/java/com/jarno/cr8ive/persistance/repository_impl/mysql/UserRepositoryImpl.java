package com.jarno.cr8ive.persistance.repository_impl.mysql;

import com.jarno.cr8ive.business.boundaries.repository.IUserRepository;
import com.jarno.cr8ive.domain.user.IUser;
import com.jarno.cr8ive.persistance.converter.UserConverter;
import com.jarno.cr8ive.persistance.repository_impl.entity.UserJpaMapper;
import com.jarno.cr8ive.persistance.repository_jpa.JpaUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements IUserRepository {
    private JpaUserRepository repository;
    private final UserConverter userConverter;


    @Override
    @Transactional
    public IUser save (IUser user){
        UserJpaMapper userJpaMapper = userConverter.toUserJpaMapper(user);
        userJpaMapper.getUserRoles().forEach(roles -> roles.setUser(userJpaMapper));
        repository.save(userJpaMapper);
        return userConverter.toUser(userJpaMapper);
    }

    @Override
    public boolean existsById(Long id) {return repository.existsById(id);}

    @Override
    public List<IUser> getUsersByName(String name){
        List<UserJpaMapper> users = repository.findUsersByNameExcludingMaintainers(name);
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

    public boolean existsByEmailAddress(String emailAddress){return repository.existsByEmailAddress(emailAddress);}
}
