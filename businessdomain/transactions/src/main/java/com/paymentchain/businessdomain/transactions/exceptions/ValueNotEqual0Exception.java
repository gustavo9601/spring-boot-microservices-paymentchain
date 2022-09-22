package com.paymentchain.businessdomain.transactions.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValueNotEqual0Exception extends RuntimeException{

    private Integer code;
    private String message;

}
