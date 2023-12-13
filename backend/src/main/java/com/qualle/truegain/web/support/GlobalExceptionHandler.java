package com.qualle.truegain.web.support;

import com.qualle.truegain.api.support.ErrorResponseDto;
import com.qualle.truegain.api.support.ErrorType;
import com.qualle.truegain.model.exception.BadRequestException;
import com.qualle.truegain.model.exception.EntityNotFoundException;
import com.qualle.truegain.model.exception.GenericApplicationException;
import com.qualle.truegain.model.exception.TokenAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ExceptionMessageRetriever messageRetriever;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleException(EntityNotFoundException exception) {
        log.warn(exception.getMessage(), exception);
        return composeResponse(exception);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(BadRequestException exception) {
        log.warn(exception.getMessage(), exception);
        return composeResponse(exception);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleRequestExceptions(Exception exception) {
        log.warn(exception.getMessage(), exception);
        return composeResponse(ErrorType.BAD_REQUEST, messageRetriever.retrieve(exception));
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDto handleAuthenticationException(AuthenticationException exception) {
        log.warn(exception.getMessage(), exception);
        return composeResponse(ErrorType.UNAUTHORIZED, messageRetriever.retrieve(exception));
    }

    @ExceptionHandler(TokenAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDto handleAuthenticationException(TokenAuthenticationException exception) {
        log.warn(exception.getMessage(), exception);
        return composeResponse(exception);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(RuntimeException exception) {
        log.warn(exception.getMessage(), exception);
        return composeResponse(ErrorType.SERVER_ERROR, messageRetriever.retrieve(exception));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception exception) {
        log.warn(exception.getMessage(), exception);
        return composeResponse(ErrorType.SERVER_ERROR, messageRetriever.retrieve(exception));
    }

    private ErrorResponseDto composeResponse(ErrorType errorType, Collection<String> errorDetails) {
        return ErrorResponseDto.builder()
                .type(errorType.toString())
                .stack(errorDetails)
//                .message()
                .build();
    }

    private ErrorResponseDto composeResponse(GenericApplicationException exception) {
        return ErrorResponseDto.builder()
                .type(exception.getErrorType().toString())
                .stack(messageRetriever.retrieve(exception))
                .additional(exception.getAdditional())
                .message(exception.getMessage())
                .build();
    }
}
