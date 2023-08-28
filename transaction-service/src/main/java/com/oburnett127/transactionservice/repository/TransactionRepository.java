package com.oburnett127.transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oburnett127.transactionservice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}