package com.qualle.shapeup.web.support;

import com.qualle.shapeup.api.support.ErrorResponseDto;
import com.qualle.shapeup.api.support.ErrorType;
import com.qualle.shapeup.model.exception.BadRequestException;
import com.qualle.shapeup.model.exception.EntityNotFoundException;
import com.qualle.shapeup.web.support.ExceptionMessageRetriever;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;
import java.util.Objects;

//@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ExceptionMessageRetriever messageRetriever;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleException(EntityNotFoundException exception) {
        String techMessages = Objects.toString(messageRetriever.retrieve(exception));
        exception.printStackTrace();
//        log.warn(techMessages);
        return composeResponse(ErrorType.NOT_FOUND, messageRetriever.retrieve(exception));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(BadRequestException exception) {
        String techMessages = Objects.toString(messageRetriever.retrieve(exception));
        exception.printStackTrace();
//        log.warn(techMessages);
        return composeResponse(ErrorType.BAD_REQUEST, messageRetriever.retrieve(exception));
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleRequestExceptions(Exception exception) {
        String techMessages = Objects.toString(messageRetriever.retrieve(exception));
        exception.printStackTrace();
//        log.warn(techMessages);
        return composeResponse(ErrorType.BAD_REQUEST, messageRetriever.retrieve(exception));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(RuntimeException exception) {
        String techMessages = Objects.toString(messageRetriever.retrieve(exception));
        exception.printStackTrace();
//        log.warn(techMessages);
        return composeResponse(ErrorType.SERVER_ERROR, messageRetriever.retrieve(exception));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception exception) {
        String techMessages = Objects.toString(messageRetriever.retrieve(exception));
        exception.printStackTrace();
//        log.warn(techMessages);
        return composeResponse(ErrorType.SERVER_ERROR, messageRetriever.retrieve(exception));
    }

    private ErrorResponseDto composeResponse(ErrorType errorType, Collection<String> errorDetails) {
        return ErrorResponseDto.builder()
                .type(errorType.toString())
                .stack(errorDetails)
//                .message(messageSource.getMessage(errorType.getMessage(), null, null))
//                .messageTitle(messageSource.getMessage(errorType.getTitle(), null, null))
                .build();
    }
}
