package com.paymentchain.customer.repositories;

import com.paymentchain.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.code = :code")
    public Customer findByCode(@Param(value = "code") String code);

    @Query("select c from Customer c where c.iban = :iban")
    public Optional<Customer> findByIban(@Param(value = "iban") String iban);

}
