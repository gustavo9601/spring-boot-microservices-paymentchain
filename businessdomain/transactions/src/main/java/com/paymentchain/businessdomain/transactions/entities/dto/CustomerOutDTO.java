package com.paymentchain.businessdomain.transactions.entities.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class CustomerOutDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String code;
    private String iban;
    private String names;
    private String surname;
    private String phone;
    private String address;


    private List<CustomerProductoOutDTO> products;

    private List<?> transactions;

}
