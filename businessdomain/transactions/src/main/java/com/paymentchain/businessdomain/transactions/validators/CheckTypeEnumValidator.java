package com.paymentchain.businessdomain.transactions.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// CharSequence // Generico para que acepte diferetes secuencias de Enum
public class CheckTypeEnumValidator implements ConstraintValidator<CheckTypeEnum, Enum> {

    private List<String> acceptedValues;

    private static final Logger log = LoggerFactory.getLogger(CheckTypeEnumValidator.class);


    @Override
    public void initialize(CheckTypeEnum constraintAnnotation) {
        // Capturando los posibles valores del enum
        this.acceptedValues = Stream.of(constraintAnnotation.enumClass()
                        .getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());

        log.info("Valores aceptados:\t{}", this.acceptedValues);
    }

    @Override
    public boolean isValid(Enum charSequence, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Valor enviado:\t{}", charSequence);
        return this.acceptedValues.contains(charSequence.toString());
    }


}
