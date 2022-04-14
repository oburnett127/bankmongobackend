package com.oburnett127.transactionservice.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionOperations {
    List<Transaction> listAll();
    void createTransaction(Transaction Transaction);
    Transaction getTransaction(UUID id);
    Transaction withdraw(UUID id, BigDecimal amount);
    Transaction deposit(UUID id, BigDecimal amount);
    Transaction depositCheck(UUID id, String fullName, String signature, BigDecimal amount);
    public Transaction transfer(UUID idSender, UUID idReceiver, BigDecimal amount);
}
