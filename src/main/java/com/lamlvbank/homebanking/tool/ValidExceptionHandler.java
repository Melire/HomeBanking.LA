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

/*
Propositos:
1) Validar si el json de entrada cumple con las restricciones que indicamos
   en nuestras entidades.
2) Spring Security: para definir que rutas estan abiertas al publico y que rutas son privadas
   por medio de roles.
3) Excepciones personalizadas
 */
@RestControllerAdvice
public class ValidExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerValidationExeption(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String errorMessage = ErrorValidationBuilder(fieldErrors);
        return ResponseEntity.badRequest().body(errorMessage);
    }
    private String ErrorValidationBuilder(List<FieldError> fieldErrors ){
        return "VALIDATION ERROR:\n"
                + fieldErrors.stream().map(fieldError -> "*Field[" + fieldError.getField() + "]:"
        + fieldError.getDefaultMessage())
                .collect(Collectors.joining("\n"));
    }

}
