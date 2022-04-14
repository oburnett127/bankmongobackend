package com.oburnett127.transactionservice.services;

import com.oburnett127.transactionservice.daos.TransactionDao;
import com.oburnett127.transactionservice.models.Transaction;
import com.oburnett127.transactionservice.utils.TransactionValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
    public Transaction getTransaction(final UUID id) {
        final var transaction = TransactionDao.getTransaction(id);

        return transaction;
    }

    @Override
    @SneakyThrows
    public Transaction withdraw(UUID id, BigDecimal amount) {
        final var transaction = transactionDao.getTransaction(id);

        transactionValidator.withdraw(transaction, amount);

        transaction.setBalance(transaction.getBalance().subtract(amount));

        transactionDao.save(transaction);

        return transaction;
    }


    @Override
    @SneakyThrows
    public Transaction transfer(UUID idSender, UUID idReceiver, BigDecimal amount) {
        final var senderTransaction = TransactionDao.getTransaction(idSender);
        final var receiverTransaction = TransactionDao.getTransaction(idReceiver);

        TransactionValidator.transfer(senderTransaction, receiverTransaction, amount);

        senderTransaction.setBalance(senderTransaction.getBalance().subtract(amount));
        receiverTransaction.setBalance(receiverTransaction.getBalance().add(amount));

        TransactionDao.save(senderTransaction);
        TransactionDao.save(receiverTransaction);

        return senderTransaction;
    }
}