package com.paymentchain.businessdomain.transactions.validators;

import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// ConstraintValidator<Anotacion a enlazar, Tipo de dato con el cual se validara>

public class ContainTextValidator implements ConstraintValidator<ContainText, String> {

    private String textShouldContain;

    private static final Logger log = LoggerFactory.getLogger(ContainTextValidator.class);

    @Override
    public void initialize(ContainText constraintAnnotation) {
        this.textShouldContain = constraintAnnotation.messageToShouldContain();
        log.info("Entra en la validacion ContainTextValidator y el texto que debe contener es=\t{}", textShouldContain);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(s) && s.contains(this.textShouldContain);
    }
}
