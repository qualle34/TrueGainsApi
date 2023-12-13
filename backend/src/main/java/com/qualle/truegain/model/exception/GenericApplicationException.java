package com.qualle.truegain.model.exception;

import lombok.Getter;
import com.qualle.truegain.api.support.ErrorType;

import java.util.Map;

@Getter
public class GenericApplicationException extends RuntimeException {

    private ErrorType errorType;
    private Map<String, String> additional;

    public GenericApplicationException(ErrorType errorType, Map<String, String> additional) {
        this.errorType = errorType;
        this.additional = additional;
    }

    public GenericApplicationException(String message, ErrorType errorType, Map<String, String> additional) {
        super(message);
        this.errorType = errorType;
        this.additional = additional;
    }

    public GenericApplicationException(String message, Throwable cause, ErrorType errorType, Map<String, String> additional) {
        super(message, cause);
        this.errorType = errorType;
        this.additional = additional;
    }

    public GenericApplicationException(Throwable cause, ErrorType errorType, Map<String, String> additional) {
        super(cause);
        this.errorType = errorType;
        this.additional = additional;
    }

    public GenericApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorType errorType, Map<String, String> additional) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorType = errorType;
        this.additional = additional;
    }
}
