package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.RegistrationDto;
import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.exception.EntityNotFoundException;
import com.qualle.truegain.model.security.UserSecurityDetails;
import com.qualle.truegain.repository.UserRepository;
import com.qualle.truegain.service.UserService;
import com.qualle.truegain.service.basic.AbstractService;
import com.qualle.truegain.service.mapper.GenericMapper;
import com.qualle.truegain.service.mapper.RegistrationUserMapper;
import com.qualle.truegain.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractService<User, UserDto, Long> implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final RegistrationUserMapper registrationMapper;

    @Override
    public UserDto getUserByLogin(String login) {
        User user = repository.findUserWithCredentials(login);

        return mapper.toDto(user, List.of("credentials"));
    }

    @Override
    public void registerUser(RegistrationDto dto) {
        User user = registrationMapper.fromDto(dto);

        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findUserWithCredentials(login);

        if (Objects.isNull(user)) {
            throw new EntityNotFoundException(String.format("User %s is not found", login));
        }

        return new UserSecurityDetails(user);
    }

    @Override
    protected CrudRepository<User, Long> getRepository() {
        return repository;
    }

    @Override
    protected GenericMapper<User, UserDto> getMapper() {
        return mapper;
    }
}
