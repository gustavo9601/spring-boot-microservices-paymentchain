package com.paymentchain.businessdomain.transactions.entities;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ApiError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private Integer code;
    private String status;
    private String description;
    private Map<String, List<String>> errors;
}
