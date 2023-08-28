package com.oburnett127.transactionservice.service;

import java.math.BigDecimal;
import java.util.List;

import com.oburnett127.transactionservice.model.Transaction;
;

public interface TransactionOperations {
    List<Transaction> listAll();
    Transaction getTransactionById(int id);
    List<Transaction> getTransactionsByAccountId(int id);
    void createTransaction(Transaction Transaction);
}
