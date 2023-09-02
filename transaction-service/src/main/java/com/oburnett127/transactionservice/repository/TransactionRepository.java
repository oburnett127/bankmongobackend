package com.oburnett127.transactionservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.oburnett127.transactionservice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.account = :accountId")
    List<Transaction> getTransactionsByAccountId(@Param("accountId") int accountId);
}