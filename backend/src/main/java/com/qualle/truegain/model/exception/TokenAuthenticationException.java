package com.qualle.truegain.model.exception;

public class TokenAuthenticationException extends RuntimeException {

    private boolean tokenExpired = false;

    public TokenAuthenticationException(String msg) {
        super(msg);
    }

    public TokenAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }


    public TokenAuthenticationException(String msg, Throwable cause, boolean tokenExpired) {
        super(msg, cause);
        this.tokenExpired = tokenExpired;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }
}
