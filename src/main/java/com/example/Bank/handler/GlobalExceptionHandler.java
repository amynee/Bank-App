package com.example.Bank.handler;

import com.example.Bank.exception.ObjectValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(ObjectValidationException exp) {
         return ExceptionResponse
                .builder()
                .errorMsg("Object not valid")
                .errorSource(exp.getViolationSource())
                .validationErrors(exp.getViolations())
                .build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(EntityNotFoundException exp) {
        return ExceptionResponse
                .builder()
                .errorMsg(exp.getMessage())
                .build();
    }
}
