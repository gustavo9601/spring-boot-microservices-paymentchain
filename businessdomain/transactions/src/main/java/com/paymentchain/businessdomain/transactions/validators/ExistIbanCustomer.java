package com.paymentchain.businessdomain.transactions.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistIbanCustomerValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ExistIbanCustomer {
    String message() default "No se encontro el Iban del cliente";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
