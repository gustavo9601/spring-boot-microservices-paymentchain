package com.paymentchain.businessdomain.transactions.validators;

import com.paymentchain.businessdomain.transactions.clients.CustomerFeignClient;
import com.paymentchain.businessdomain.transactions.entities.dto.CustomerOutDTO;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistIbanCustomerValidator implements ConstraintValidator<ExistIbanCustomer, String> {

    @Autowired
    private CustomerFeignClient customerFeignClient;

    private static final Logger log = LoggerFactory.getLogger(ExistIbanCustomerValidator.class);


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        log.info("iban enviado:\t{}", s);
        try {
            ResponseEntity<CustomerOutDTO> customerResponse = this.customerFeignClient.getCustomerByIban(s);
            if (customerResponse.getStatusCode() == HttpStatus.OK) {
                return true;
            }
        } catch (FeignException e) {
            log.error("Mensaje error no controlado por el Feign Exeption:\t{}", e.getMessage());
            return false;
        }


        return false;
    }
}
