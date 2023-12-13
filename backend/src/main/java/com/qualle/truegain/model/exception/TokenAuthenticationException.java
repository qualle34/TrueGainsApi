package com.qualle.truegain.model.exception;

import com.qualle.truegain.api.support.ErrorType;

public class TokenAuthenticationException extends GenericApplicationException {

    public TokenAuthenticationException(String message) {
        super(message, ErrorType.ACCESS_DENIED, null);
    }

    public TokenAuthenticationException(String message, Throwable cause) {
        super(message, cause, ErrorType.ACCESS_DENIED, null);
    }

    public TokenAuthenticationException(String message, Throwable cause, ErrorType errorType) {
        super(message, cause, ErrorType.ACCESS_DENIED, null);
    }
}
