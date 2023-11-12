package com.qualle.truegain.service.security;

import com.qualle.truegain.api.support.ErrorType;
import com.qualle.truegain.config.property.AuthenticationProperties;
import com.qualle.truegain.model.exception.TokenAuthenticationException;
import com.qualle.truegain.model.security.TokenClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.crypto.SecretKey;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JwtTokenService implements TokenService {

    private SecretKey key = null;
    private final AuthenticationProperties properties;

    @PostConstruct
    private void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getToken().getSecret()));
    }

    @Override
    public String generateAccess(TokenClaims claims) {

        return Jwts.builder()
                .id(claims.getTokenId())
                .claim("roles", claims.getRoles())
                .claim("uid", claims.getUserId())
                .claim("type", "access")
                .subject(claims.getSubject())
                .issuer(claims.getIssuedBy())
                .notBefore(claims.getIssuedAt())
                .issuedAt(claims.getIssuedAt())
                .expiration(claims.getAccessExpiredAt())
                .signWith(key)
                .compact();
    }

    @Override
    public String generateRefresh(TokenClaims claims) {
        return Jwts.builder()
                .id(claims.getTokenId())
                .claim("session", claims.getSessionId())
                .claim("uid", claims.getUserId())
                .claim("type", "refresh")
                .subject(claims.getSubject())
                .issuer(claims.getIssuedBy())
                .issuedAt(claims.getIssuedAt())
                .expiration(claims.getRefreshExpiredAt())
                .signWith(key)
                .compact();
    }

    @Override
    public String generateTemporary(TokenClaims claims) {
        return Jwts.builder()
                .id(claims.getTokenId())
                .claim("uid", claims.getUserId())
                .claim("type", "temp")
                .issuer(claims.getIssuedBy())
                .issuedAt(claims.getIssuedAt())
                .expiration(claims.getTemporaryExpiredAt())
                .signWith(key)
                .compact();
    }

    @Override
    public TokenClaims validate(String token) {

        try {
            Claims payload = Jwts.parser()
                    .verifyWith(key).build()
                    .parseSignedClaims(token)
                    .getPayload();

            TokenClaims.TokenClaimsBuilder claims = TokenClaims.builder()
                    .tokenId(payload.getId())
                    .issuedAt(payload.getIssuedAt())
                    .issuedBy(payload.getIssuer())
                    .userId(payload.get("uid", Long.class));

            switch (payload.get("type").toString()) {
                case "access" -> claims
                        .subject(payload.getSubject())
                        .roles(payload.get("roles", List.class))
                        .accessExpiredAt(payload.getExpiration());

                case "refresh" -> claims
                        .subject(payload.getSubject())
                        .sessionId(payload.get("session", String.class))
                        .refreshExpiredAt(payload.getExpiration());

                case "temp" -> claims
                        .temporaryExpiredAt(payload.getExpiration());
            }

            return claims.build();
        } catch (ExpiredJwtException e) {
            throw new TokenAuthenticationException(e.getMessage(), e, ErrorType.EXPIRED_TOKEN);

        } catch (JwtException e) {
            throw new TokenAuthenticationException(e.getMessage(), e);
        }

    }

}