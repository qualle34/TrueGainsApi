package com.qualle.truegain.model.exception;

import com.qualle.truegain.api.support.ErrorType;

public class EntityNotFoundException extends GenericApplicationException {

    public EntityNotFoundException() {
        super(ErrorType.NOT_FOUND, null);
    }

    public EntityNotFoundException(String message) {
        super(message, ErrorType.NOT_FOUND, null);
    }
}
