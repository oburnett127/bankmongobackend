package com.oburnett127.transactionservice.services;

import com.oburnett127.transactionservice.daos.TransactionDao;
import com.oburnett127.transactionservice.models.Transaction;
import com.oburnett127.transactionservice.utils.TransactionValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
;

@Service
@Slf4j
public class TransactionService implements TransactionOperations {

    private final TransactionDao transactionDao;
    private final TransactionValidator transactionValidator;

    public TransactionService(final TransactionDao transactionDao, final TransactionValidator transactionValidator) {
        this.transactionDao = transactionDao;
        this.transactionValidator = transactionValidator;
    }

    @Override
    public List<Transaction> listAll() {
        return this.transactionDao.getAll();
    }

    @Override
    public void createTransaction(Transaction transaction) {
        this.transactionDao.create(transaction);
    }

    @Override
    @SneakyThrows
    public Transaction getTransaction(final int id) {
        final var transaction = transactionDao.getTransaction(id);

        return transaction;
    }

//    @Override
//    @SneakyThrows
//    public Transaction withdraw(int id, BigDecimal amount) {
//        final var transaction = transactionDao.getTransaction(id);
//
//        transactionValidator.withdraw(transaction, amount);
//
//        transaction.setBalance(transaction.getBalance().subtract(amount));
//
//        transactionDao.save(transaction);
//
//        return transaction;
//    }
}