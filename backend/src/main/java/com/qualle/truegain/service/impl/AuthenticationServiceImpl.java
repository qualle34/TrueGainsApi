package com.qualle.truegain.service.impl;

import com.qualle.truegain.api.LoginPasswordAuthenticationDto;
import com.qualle.truegain.api.RefreshTokenAuthenticationDto;
import com.qualle.truegain.api.TokenDto;
import com.qualle.truegain.api.UserDto;
import com.qualle.truegain.config.property.AuthenticationProperties;
import com.qualle.truegain.model.entity.Session;
import com.qualle.truegain.model.entity.User;
import com.qualle.truegain.model.security.TokenClaims;
import com.qualle.truegain.service.AuthenticationService;
import com.qualle.truegain.service.SessionService;
import com.qualle.truegain.service.UserService;
import com.qualle.truegain.service.security.TokenService;
import com.qualle.truegain.service.util.DateFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final TokenService tokenService;
    private final SessionService sessionService;

    private final AuthenticationProperties authenticationProperties;

    private final AuthenticationManager authenticationManager;

    @Override
    public TokenDto authenticate(LoginPasswordAuthenticationDto dto) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));

        UserDto user = userService.getUserWithCredentialsByLogin(dto.getLogin());

        return token(user);
    }

    @Override
    public TokenDto token(long userId) {
        UserDto user = userService.getUserWithCredentialsById(userId);

        return token(user);
    }

    private TokenDto token(UserDto user) {

        String tokenId = UUID.randomUUID().toString();
        Date now = new Date();
        Date expired = new Date(now.getTime() + authenticationProperties.getToken().getLifetime().toMillis());
        Date sessionExpired = new Date(now.getTime() + authenticationProperties.getSession().getLifetime().toMillis());

        Session session = Session.builder()
                .tokenId(tokenId)
                .createdAt(now.getTime())
                .expiredAt(sessionExpired.getTime())
                .build();

        User user1 = User.builder().id(user.getId()).build();
        user1.setSessions(List.of(session));

        session.setUser(user1);

        Session createdSession = sessionService.save(session);

        TokenClaims claims = TokenClaims.builder()
                .subject(user.getCredentials().getLogin())
                .roles(List.of("USER"))
                .userId(user.getId())
                .sessionId(createdSession.getId().toString())
                .tokenId(tokenId)
                .issuedBy(authenticationProperties.getToken().getIssuedBy())
                .issuedAt(now)
                .accessExpiredAt(expired)
                .refreshExpiredAt(sessionExpired)
                .build();


        String accessToken = tokenService.generateAccess(claims);
        String refreshToken = tokenService.generateRefresh(claims);

        return TokenDto.builder()
                .accessToken(accessToken)
                .accessTokenExpiredAt(DateFormatUtil.toString(LocalDateTime.ofInstant(expired.toInstant(), ZoneId.systemDefault())))
                .refreshToken(refreshToken)
                .refreshTokenExpiredAt(DateFormatUtil.toString(LocalDateTime.ofInstant(sessionExpired.toInstant(), ZoneId.systemDefault())))
                .build();
    }

    @Override
    public TokenDto refresh(RefreshTokenAuthenticationDto dto) {
        TokenClaims claims = tokenService.validate(dto.getToken());

        Session session = sessionService.getByIdWithUser(claims.getSessionId());

        String tokenId = UUID.randomUUID().toString();
        Date now = new Date();
        Date expired = new Date(now.getTime() + authenticationProperties.getToken().getLifetime().toMillis());
        Date sessionExpired = new Date(now.getTime() + authenticationProperties.getSession().getLifetime().toMillis());

        session.setExpiredAt(sessionExpired.getTime());
        session.setCreatedAt(now.getTime());

        Session createdSession = sessionService.update(session);

        claims.setRoles(List.of("USER"));
        claims.setSessionId(createdSession.getId().toString());
        claims.setTokenId(tokenId);
        claims.setIssuedAt(now);
        claims.setAccessExpiredAt(expired);
        claims.setRefreshExpiredAt(sessionExpired);

        String accessToken = tokenService.generateAccess(claims);
        String refreshToken = tokenService.generateRefresh(claims);

        return TokenDto.builder()
                .accessToken(accessToken)
                .accessTokenExpiredAt(LocalDateTime.ofInstant(expired.toInstant(), ZoneId.systemDefault()).toString())
                .refreshToken(refreshToken)
                .refreshTokenExpiredAt(LocalDateTime.ofInstant(sessionExpired.toInstant(), ZoneId.systemDefault()).toString())
                .build();
    }

    @Override
    public void logout(RefreshTokenAuthenticationDto dto) {
        TokenClaims claims = tokenService.validate(dto.getToken());
        sessionService.deleteById(claims.getSessionId());
    }
}
