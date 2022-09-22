package com.paymentchain.businessdomain.transactions.repositories;

import com.paymentchain.businessdomain.transactions.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
