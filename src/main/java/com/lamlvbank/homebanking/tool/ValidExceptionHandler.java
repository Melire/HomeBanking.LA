package com.lamlvbank.homebanking.tool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult=ex.getBindingResult();
        List<FieldError> fieldErrors=bindingResult.getFieldErrors();
        String errorMessages=errorValidationBuilder(fieldErrors);
        return ResponseEntity.badRequest().body(errorMessages);
    }

    private String errorValidationBuilder(List<FieldError> fieldErrors){
        return "VALIDATION ERROR:\n"
                +fieldErrors.stream().map(fieldError -> "*Field["+ fieldError.getField()+"]:"
                +fieldError.getDefaultMessage())
                .collect(Collectors.joining("\n"));
    }
}
