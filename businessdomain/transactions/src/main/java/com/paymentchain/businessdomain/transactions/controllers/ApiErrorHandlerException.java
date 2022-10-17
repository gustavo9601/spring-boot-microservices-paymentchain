package com.paymentchain.businessdomain.transactions.controllers;

import com.paymentchain.businessdomain.transactions.entities.ApiError;
import com.paymentchain.businessdomain.transactions.exceptions.ValidateFieldsException;
import com.paymentchain.businessdomain.transactions.exceptions.ValueNotEqual0Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiErrorHandlerException extends Exception {


    private static final Logger log = LoggerFactory.getLogger(ApiErrorHandlerException.class);


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ApiError> methodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {

        log.info("Error en methodArgumentNotValidException - Mensaje: {}", exception.getMessage());
        log.info("Error en methodArgumentNotValidException - Clase: {}", exception.getClass());

        Map<String, List<String>> errors = new HashMap<>();

        exception.getFieldErrors()
                .forEach(error -> {
                    if (errors.containsKey(error.getField())) {
                        errors.get(error.getField()).add(error.getDefaultMessage());
                    } else {
                        errors.put(error.getField(), List.of(error.getDefaultMessage()));
                    }
                });

        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .description("Alguno de los valores es incorrecto")
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    // La anotacion recibe diferentes tipos de exepcion a generar
    @ExceptionHandler({ValueNotEqual0Exception.class})
    public final ResponseEntity<ApiError> handleExceptionValueNotEqual0Exception(Exception exception, WebRequest webRequest) {

        log.info("Error en handleExceptionValueNotEqual0Exception - Mensaje: {}", exception.getMessage());
        log.info("Error en handleExceptionValueNotEqual0Exception - Clase: {}", exception.getClass());

        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .description("El valor no puede ser 0")
                .code(HttpStatus.BAD_REQUEST.value())
                .errors(null)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public final ResponseEntity<ApiError> handleException(Exception exception, WebRequest webRequest) {

        log.info("Error en handleException - Mensaje: {}", exception.getMessage());
        log.info("Error en handleException - Clase: {}", exception.getClass());

        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .description("Alguno de los valores es incorrecto")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errors(null)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }


    @ExceptionHandler({ValidateFieldsException.class})
    public final ResponseEntity<ApiError> handleValidationException(Exception exception,
                                                                    WebRequest webRequest,
                                                                    ValidateFieldsException validateFieldsException
    ) {
        log.info("Error en handleValidationException - Mensaje: {}", exception.getMessage());
        log.info("Error en handleValidationException - Clase: {}", exception.getClass());
        log.info("Errores en handler: {}", validateFieldsException.getErrors());


        Map<String, List<String>> errors = new HashMap<>();

        validateFieldsException.getErrors()
                .forEach(error -> {

                    if (errors.containsKey(error.getField())) {
                        errors.get(error.getField()).add(error.getDefaultMessage());
                    } else {
                        errors.put(error.getField(), List.of(error.getDefaultMessage()));
                    }

                });


        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .description("Alguno de los campos es invalido")
                .code(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


}
