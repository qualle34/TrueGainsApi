package com.qualle.truegain.model.security;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenClaims {

    private String subject;
    private List<String> roles;
    private String sessionId;
    private String tokenId;
    private long userId;
    private String issuedBy;
    private Date issuedAt;
    private Date accessExpiredAt;
    private Date refreshExpiredAt;
    private Date temporaryExpiredAt;

}
