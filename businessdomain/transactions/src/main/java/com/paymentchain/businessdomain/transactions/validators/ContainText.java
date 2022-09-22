package com.paymentchain.businessdomain.transactions.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ContainTextValidator.class) // Enlaza la clase que tiene la logica
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ContainText {
    /*
     * Parametros por default, que podra recibir la anotacion
     * */

    String message() default "El texto debe contener ***";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String messageToShouldContain() default "***";

}
