package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.api.support.ErrorType;
import com.qualle.truegain.model.entity.Confirmation;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.exception.BadRequestException;
import com.qualle.truegain.model.exception.EntityNotFoundException;
import com.qualle.truegain.model.security.UserSecurityDetails;
import com.qualle.truegain.repository.ConfirmationRepository;
import com.qualle.truegain.repository.UserRepository;
import com.qualle.truegain.service.UserService;
import com.qualle.truegain.service.basic.AbstractService;
import com.qualle.truegain.service.mapper.GenericMapper;
import com.qualle.truegain.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractService<User, UserDto, Long> implements UserService {

    private final UserRepository repository;
    private final ConfirmationRepository confirmationRepository;
    private final UserMapper mapper;

    @Override
    public UserDto getUserWithCredentialsByLogin(String login) {
        User user = repository.findUserWithCredentials(login);

        return mapper.toDto(user, List.of("image", "credentials"));
    }

    @Override
    public UserDto getUserWithCredentialsById(long id) {
        User user = repository.findUserWithCredentials(id);

        return mapper.toDto(user, List.of("credentials"));
    }

    @Override
    @Transactional(dontRollbackOn = BadRequestException.class)
    public void verifyUser(long userId, int code) {
        User user = repository.findUserWithConfirmation(userId);

        Confirmation confirmation = user.getConfirmation();

        if (confirmation == null) {
            throw new BadRequestException("Unable to confirm user registration. Confirmation is not available.", ErrorType.CONFIRMATION_FAIL);
        }

        if (confirmation.getCode() != code) {

            if (confirmation.getFails() >= 2) {
                user.setLocked(true);
                confirmationRepository.updateConfirmationFailsForUser(userId, confirmation.getFails() + 1);
                repository.save(user);
                throw new BadRequestException("Unable to confirm user registration. Confirmation code is not valid. User locked.", ErrorType.CONFIRMATION_FAIL, Map.of("reason", "incorrect_code_user_locked"));
            }

            confirmationRepository.updateConfirmationFailsForUser(userId, confirmation.getFails() + 1);

            throw new BadRequestException("Unable to confirm user registration. Confirmation code is not valid.", ErrorType.CONFIRMATION_FAIL, Map.of("reason", "incorrect_code"));
        }

        confirmationRepository.deleteConfirmationForUser(userId);

        user.setConfirmation(null);
        user.setEnabled(true);
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
