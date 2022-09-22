package com.paymentchain.businessdomain.transactions.enums;

public enum Status {

    PENDIENTE("Pendiente", 0),
    ACEPTADA("Aceptada", 1),
    RECHAZADA("Rechazada", 2),
    CANCELADA("Cancelada", 3);

    private String status;
    private Long statusId;

    Status(String status) {
        this.status = status;
    }

    Status(String status, long statusId) {
        this.status = status;
        this.statusId = statusId;
    }
}
