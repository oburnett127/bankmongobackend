package com.oburnett127.transactionservice.service;

import com.oburnett127.transactionservice.model.Transaction;
import com.oburnett127.transactionservice.repository.TransactionRepository;
import com.oburnett127.transactionservice.util.TransactionValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class TransactionService implements TransactionOperations {

    private final TransactionRepository transactionRepository;
    private final TransactionValidator transactionValidator;

    public TransactionService(final TransactionRepository transactionRepository, final TransactionValidator transactionValidator) {
        this.transactionRepository = transactionRepository;
        this.transactionValidator = transactionValidator;
    }

    @Override
    public List<Transaction> listAll() {
        return this.transactionRepository.findAll();
    }

    @Override
    @SneakyThrows
    public Transaction getTransactionById(final int id) {
        final var transaction = transactionRepository.getReferenceById(id);
        return transaction;
    }

    @Override
    @SneakyThrows
    public List<Transaction> getTransactionsByAccountId(final int id) {
        final var transactions = transactionRepository.getTransactionsByAccountId(id);
        return transactions;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

//    @Override
//    @SneakyThrows
//    public Transaction withdraw(int id, BigDecimal amount) {
//        final var transaction = transactionRepository.getTransaction(id);
//
//        transactionValidator.withdraw(transaction, amount);
//
//        transaction.setBalance(transaction.getBalance().subtract(amount));
//
//        transactionRepository.save(transaction);
//
//        return transaction;
//    }
}