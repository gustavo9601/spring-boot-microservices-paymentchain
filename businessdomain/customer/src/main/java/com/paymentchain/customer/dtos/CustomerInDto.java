package com.paymentchain.customer.dtos;

import com.paymentchain.customer.entities.CustomerProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/*
* DTO documentado con swagger
* */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Customer In DTO")
public class CustomerInDto {

    @Schema(description = "Customer name", example = "Customer 1", required = true)
    private String name;
    @Schema(description = "Customer code", example = "CUST-001", required = true)
    private String code;
    @Schema(description = "Customer IBAN", example = "ES1234567890123456789012", required = true)
    private String iban;
    @Schema(description = "Customer names", example = "Customer 1ss", required = true)
    private String names;
    @Schema(description = "Customer lastname", example = "Customer 1ss", required = true)
    private String lastname; // En la entidad es surname, pero en e mapeo se cambia a lastname
    @Schema(description = "Customer phone", example = "123456789", required = true)
    private String phone;
    @Schema(description = "Customer address", example = "Customer 1ss", required = true)
    private String address;

    @Schema(description = "Customer products", required = false, example = "[{\"productId\":1,\"productName\":\"Product 1\",\"productCode\":\"PROD-001\"}]")
    private List<CustomerProduct> products;

    @Transient
    @Schema(description = "Customer transactions", required = false, example = "[{\"id\":1,\"amount\":100,\"description\":\"Transaction 1\",\"date\":\"2020-01-01\"}]")
    private List<Object> transactions;

}
