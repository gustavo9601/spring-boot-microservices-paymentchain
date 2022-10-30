package com.paymentchain.customer.exception;

import com.paymentchain.customer.common.StandarizedApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<StandarizedApiExceptionResponse> handleNoContentException(IOException ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse();
        response.setCode(HttpStatus.NO_CONTENT.value());
        response.setDetail(ex.getMessage());
        response.setInstance(ex.getLocalizedMessage());
        response.setTitle("No Content");
        response.setType("https://httpstatuses.com/204");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    /*
     * Generico
     * */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<StandarizedApiExceptionResponse> handleGenericException(Exception ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setDetail(ex.getMessage());
        response.setInstance(ex.getLocalizedMessage());
        response.setTitle("Internal Server Error");
        response.setType("https://httpstatuses.com/500");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(BussinesRuleException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleBussinesRuleException(BussinesRuleException ex) {
        StandarizedApiExceptionResponse response = StandarizedApiExceptionResponse.builder()
                .code(ex.getHttpStatus().value())
                .detail(ex.getMessage())
                .instance(ex.getLocalizedMessage())
                .title(ex.getHttpStatus().getReasonPhrase())
                .type(ex.getHttpStatus().toString())
                .build();
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }

}
