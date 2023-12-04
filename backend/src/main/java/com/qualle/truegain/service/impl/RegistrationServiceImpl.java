package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.ConfirmRegistrationDto;
import com.qualle.truegain.api.NewRegistrationDto;
import com.qualle.truegain.api.RefreshTokenAuthenticationDto;
import com.qualle.truegain.api.TokenDto;
import com.qualle.truegain.api.support.ErrorType;
import com.qualle.truegain.config.property.AuthenticationProperties;
import com.qualle.truegain.model.email.UserEmail;
import com.qualle.truegain.model.email.VerificationCode;
import com.qualle.truegain.model.entity.Confirmation;
import com.qualle.truegain.model.entity.Credentials;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.exception.BadRequestException;
import com.qualle.truegain.model.security.TokenClaims;
import com.qualle.truegain.repository.UserRepository;
import com.qualle.truegain.service.AuthenticationService;
import com.qualle.truegain.service.EmailService;
import com.qualle.truegain.service.RegistrationService;
import com.qualle.truegain.service.UserService;
import com.qualle.truegain.service.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository repository;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationProperties authenticationProperties;

    @Override
    public RefreshTokenAuthenticationDto register(NewRegistrationDto dto) {

        validate(dto);

        UserEmail email = UserEmail.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
        VerificationCode verification = emailService.sendVerificationLetter(email);

        User user = User.builder()
                .name(dto.getName())
                .birthday(dto.getBirthday())
                .gender(dto.getGender())
                .build();

        Credentials credentials = Credentials.builder()
                .role("USER")
                .email(dto.getEmail())
                .login(dto.getLogin())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        user.setCredentials(credentials);

        Confirmation confirmation = Confirmation.builder()
                .createdAt(LocalDateTime.now())
                .code(verification.getCode())
                .build();

        user.setConfirmation(confirmation);

        User savedUser = repository.save(user);

        String tokenId = UUID.randomUUID().toString();
        Date now = new Date();
        Date sessionExpired = new Date(now.getTime() + authenticationProperties.getTemporary().getLifetime().toMillis());

        TokenClaims claims = TokenClaims.builder()
                .tokenId(tokenId)
                .userId(savedUser.getId())
                .issuedBy(authenticationProperties.getToken().getIssuedBy())
                .issuedAt(now)
                .temporaryExpiredAt(sessionExpired)
                .build();

        String token = tokenService.generateTemporary(claims);

        return RefreshTokenAuthenticationDto.builder()
                .token(token)
                .build();
    }

    @Override
    public TokenDto confirm(ConfirmRegistrationDto dto) {

        TokenClaims claims = tokenService.validate(dto.getToken());

        userService.verifyUser(claims.getUserId(), dto.getCode());

        return authenticationService.token(claims.getUserId());
    }

    private void validate(NewRegistrationDto dto) {
        List<User> users = repository.findUserWithCredentialsByLoginOrEmail(dto.getLogin(), dto.getEmail());

        if (!users.isEmpty()) {

            for (User user : users) {

                if (dto.getLogin().equals(user.getCredentials().getLogin())) {
                    throw new BadRequestException("Registration is impossible, an entity with the same login was found in the database", ErrorType.VALIDATION_FAIL, Map.of("reason", "login_found"));
                }
            }

            for (User user : users) {

                if (dto.getEmail().equals(user.getCredentials().getEmail())) {
                    throw new BadRequestException("Registration is impossible, an entity with the same email was found in the database", ErrorType.VALIDATION_FAIL, Map.of("reason", "email_found"));
                }
            }
        }

        if (dto.getPassword().length() < 8) {
            throw new BadRequestException("Registration is impossible, short password", ErrorType.VALIDATION_FAIL, Map.of("reason", "week_password"));
        }
    }
}
