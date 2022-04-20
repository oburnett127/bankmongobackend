package com.oburnett127.transactionservice.daos;

import com.oburnett127.transactionservice.mappers.TransactionMapper;
import com.oburnett127.transactionservice.models.Transaction;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
;

@Repository
public class TransactionDao {
    private final SqlSessionFactory sqlSessionFactory;

    public TransactionDao(final SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Transaction> getAll() {
        try (final var session = sqlSessionFactory.openSession()) {
            final var mapper = session.getMapper(TransactionMapper.class);
            final var transactions = mapper.getAll();
            return transactions;
        }
    }

    public Transaction getTransactionById(final int id) {
        try (final var session = sqlSessionFactory.openSession()) {
            final var mapper = session.getMapper(TransactionMapper.class);
            final var transaction = mapper.getTransactionById(id);
            return transaction;
        }
    }

    public List<Transaction> getTransactionsByAccountId(final int id) {
        try (final var session = sqlSessionFactory.openSession()) {
            final var mapper = session.getMapper(TransactionMapper.class);
            final var transactions = mapper.getTransactionsByAccountId(id);
            return transactions;
        }
    }

    public void create(final Transaction transaction) {
        try (final var session = sqlSessionFactory.openSession()) {
            final var mapper = session.getMapper(TransactionMapper.class);
            mapper.create(transaction);
            session.commit();
        }
    }

//    public void save(final Transaction transaction) {
//        try (final var session = sqlSessionFactory.openSession()) {
//            final var mapper = session.getMapper(TransactionMapper.class);
//            mapper.save(transaction);
//            session.commit();
//        }
//    }
}