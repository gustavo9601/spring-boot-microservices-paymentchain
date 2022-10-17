package com.paymentchain.businessdomain.transactions.repositories;

import com.paymentchain.businessdomain.transactions.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.ibanAccount = :ibanAccount")
    List<Transaction> findByIbanAccount(@Param(value = "ibanAccount") String ibanAccount);
}
