package com.paymentchain.customer.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandarizedApiExceptionResponse {
    private String type;
    private String title;
    private String detail;
    private String instance;
    private int code;
}
