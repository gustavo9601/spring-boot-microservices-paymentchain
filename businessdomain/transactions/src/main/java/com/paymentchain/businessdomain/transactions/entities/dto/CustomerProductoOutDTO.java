package com.paymentchain.businessdomain.transactions.entities.dto;

import java.io.Serializable;

public class CustomerProductoOutDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long productId;

    private String productName;

    private CustomerOutDTO customer;
}



