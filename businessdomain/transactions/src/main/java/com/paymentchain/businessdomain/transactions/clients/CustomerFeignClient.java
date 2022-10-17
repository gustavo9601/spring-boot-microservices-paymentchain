package com.paymentchain.businessdomain.transactions.clients;

import com.paymentchain.businessdomain.transactions.entities.dto.CustomerOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer", url = "${url.customers}")
public interface CustomerFeignClient {

    @GetMapping("/iban/{iban}")
    ResponseEntity<CustomerOutDTO> getCustomerByIban(@PathVariable("iban") String iban);
}
