package com.oburnett127.transactionservice.services;

import com.oburnett127.transactionservice.models.Transaction;

import java.math.BigDecimal;
import java.util.List;
;

public interface TransactionOperations {
    List<Transaction> listAll();
    void createTransaction(Transaction Transaction);
    Transaction getTransaction(int id);
}
