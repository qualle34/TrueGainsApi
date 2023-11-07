package com.qualle.truegain.service.security;

import com.qualle.truegain.model.security.TokenClaims;

public interface TokenService {

    String generateAccess(TokenClaims claims);

    String generateRefresh(TokenClaims claims);

    TokenClaims validate(String token);
}
