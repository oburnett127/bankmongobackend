package com.oburnett127.transactionservice.service;

import com.oburnett127.transactionservice.model.Transaction;
import com.oburnett127.transactionservice.repository.TransactionRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class TransactionService implements TransactionOperations {

    private final TransactionRepository transactionRepository;

    public TransactionService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> listAll() {
        return this.transactionRepository.findAll();
    }

    @Override
    @SneakyThrows
    public Transaction getTransactionById(final int id) {
        final Transaction transaction = transactionRepository.getReferenceById(id);
        return transaction;
    }

    @Override
    @SneakyThrows
    public List<Transaction> getTransactionsByAccountId(final int id) {
        final List<Transaction> transactions = transactionRepository.getTransactionsByAccountId(id);
        return transactions;
    }

    @Override
    public void createTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }
}