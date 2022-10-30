package com.paymentchain.customer.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BussinesRuleException extends Exception {

    private long id;
    private String code;
    private HttpStatus httpStatus;

}
