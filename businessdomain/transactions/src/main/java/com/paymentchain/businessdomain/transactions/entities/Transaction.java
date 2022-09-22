package com.paymentchain.businessdomain.transactions.entities;

import com.paymentchain.businessdomain.transactions.enums.Channel;
import com.paymentchain.businessdomain.transactions.enums.Status;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
@ToString
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
