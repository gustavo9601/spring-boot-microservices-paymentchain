package com.paymentchain.businessdomain.transactions.entities.dto;

import com.paymentchain.businessdomain.transactions.enums.Channel;
import com.paymentchain.businessdomain.transactions.enums.Status;
import com.paymentchain.businessdomain.transactions.validators.CheckTypeEnum;
import com.paymentchain.businessdomain.transactions.validators.ContainText;
import com.paymentchain.businessdomain.transactions.validators.ExistIbanCustomer;
import com.paymentchain.businessdomain.transactions.validators.ValueNotEqual0;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    @NotEmpty
    private String reference;

    @NotEmpty
    @ExistIbanCustomer
    private String ibanAccount;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @ValueNotEqual0
    private Double amount;

    @Min(0)
    @Max(100)
    private Double fee;

    // Validacion personalizada pasando parametro
    // @ContainText(messageToShouldContain = "Hello World =)", message = "El texto no contiene el texto esperado")
    private String description;

    @NotNull
    @CheckTypeEnum(enumClass = Status.class)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @NotNull
    @CheckTypeEnum(enumClass = Channel.class)
    @Enumerated(EnumType.ORDINAL)
    private Channel channel;

}

