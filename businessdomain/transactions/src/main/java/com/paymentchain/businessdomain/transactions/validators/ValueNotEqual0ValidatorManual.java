package com.paymentchain.businessdomain.transactions.validators;

import com.paymentchain.businessdomain.transactions.entities.dto.TransactionInDTO;
import com.paymentchain.businessdomain.transactions.exceptions.ValueNotEqual0Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

/*
 * Validador que genera un exepcion y la retorna
 * */
@Component
public class ValueNotEqual0ValidatorManual implements Validator {

    private static final Logger log = LoggerFactory.getLogger(ValueNotEqual0ValidatorManual.class);


    @Override
    public boolean supports(Class<?> aClass) {
        // Si el objeto que se va a validar es del tipo Usuario, se devuelve true
        return false;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        log.info("Entra en la validacion ValueNotEqual0Validator=\t{}", obj);

        TransactionInDTO value = (TransactionInDTO) obj;

        if (value.getAmount() == 0) {
            throw new ValueNotEqual0Exception(400, "El valor no puede ser 0");
        }

    }

}
