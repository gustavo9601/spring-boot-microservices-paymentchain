package com.paymentchain.businessdomain.transactions.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateFieldsException extends RuntimeException {

    private String message;
    private List<FieldError> errors;

}
