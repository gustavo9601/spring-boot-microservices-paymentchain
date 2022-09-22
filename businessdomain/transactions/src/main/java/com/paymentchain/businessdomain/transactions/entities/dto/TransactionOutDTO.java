package com.paymentchain.businessdomain.transactions.entities.dto;

import com.paymentchain.businessdomain.transactions.enums.Channel;
import com.paymentchain.businessdomain.transactions.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionOutDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String reference;
    private String ibanAccount;
    private LocalDateTime date;
    private Double amount;
    private Double fee;
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
    @Enumerated(EnumType.ORDINAL)
    private Channel channel;

}

