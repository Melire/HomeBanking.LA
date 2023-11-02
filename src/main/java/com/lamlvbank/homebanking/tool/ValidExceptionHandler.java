package com.lamlvbank.homebanking.tool;

//responde ante el disparador de excepcion

//metodos para cumplir con excepciones

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

@RestControllerAdvice // Complemento al controlador para no escribir mucha lógica
public class ValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class) // Este handlet se ejecuta cuando se cumplan estas dos
                                                             // excepciones
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handlerValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult(); // Saca los resultados de la excepción
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();// Creamos una lista de campos de errores por los
                                                                      // que se disparo la excepcion
        String errorMessages = errorValidationBuilder(fieldErrors);// Convertimos los errores en String
        return ResponseEntity.badRequest().body(errorMessages);

    }

    // filtro de errores, nombre del campo y detalle
    private String errorValidationBuilder(List<FieldError> fieldErrors) {
        return "VALIDATION ERROR:\n" // fieldsErrors.stream() recorre la lista de manera interna
                                     // .map()recibe una funcion y extrae datos y devuelve una instancia de tipo
                                     // string

                + fieldErrors.stream().map(fieldError -> "*FIELD[" + fieldError.getField() + "]:"// Se toma el nombre
                                                                                                 // del error del campo
                        + fieldError.getDefaultMessage()) // Se toma el detalle del error
                        .collect(Collectors.joining("\n")); // Recolecta las respuestas y las une en una sola cadena de
                                                            // String

    }

    // CIRCUITO DE DATOS,
    // llega el json, el controller pide datos a la validation @min @size etc
    // si no cumple con alguna validation tira una excepcion
    // Toma una expecion
    // se desarma la excepcion en nombre de campo y detalle de por que dio error
}
