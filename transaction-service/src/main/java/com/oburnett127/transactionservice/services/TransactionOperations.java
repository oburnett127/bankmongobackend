package com.oburnett127.transactionservice.services;

import com.oburnett127.transactionservice.models.Transaction;

import java.math.BigDecimal;
import java.util.List;
;

public interface TransactionOperations {
    List<Transaction> listAll();
    Transaction getTransactionById(int id);
    List<Transaction> getTransactionsByAccountId(int id);
    void createTransaction(Transaction Transaction);
}
