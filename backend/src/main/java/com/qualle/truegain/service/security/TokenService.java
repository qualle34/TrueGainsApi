package com.qualle.truegain.service.security;

import com.qualle.truegain.model.security.TokenClaims;

public interface TokenService {

    String generateAccess(TokenClaims claims);

    String generateRefresh(TokenClaims claims);

    String generateTemporary(TokenClaims claims);

    TokenClaims validate(String token);
}
