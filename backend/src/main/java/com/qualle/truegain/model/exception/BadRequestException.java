package com.qualle.truegain.model.exception;

import com.qualle.truegain.api.support.ErrorType;

import java.util.Map;

public class BadRequestException extends GenericApplicationException {

    public BadRequestException() {
        super(null, null);
    }

    public BadRequestException(ErrorType errorType) {
        super(errorType, null);
    }

    public BadRequestException(ErrorType errorType, Map<String, String> additional) {
        super(errorType, additional);
    }

    public BadRequestException(String message, ErrorType errorType) {
        super(message, errorType, null);
    }

    public BadRequestException(String message, ErrorType errorType, Map<String, String> additional) {
        super(message, errorType, additional);
    }

    public BadRequestException(String message, Throwable cause, ErrorType errorType, Map<String, String> additional) {
        super(message, cause, errorType, additional);
    }
}
