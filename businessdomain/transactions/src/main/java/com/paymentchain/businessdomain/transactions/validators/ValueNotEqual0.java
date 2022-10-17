package com.paymentchain.businessdomain.transactions.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValueNotEqual0Validator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValueNotEqual0 {

    String message() default "El valor no puede ser 0 :(";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
