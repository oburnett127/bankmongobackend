package com.oburnett127.accountservice.daos;

import com.oburnett127.accountservice.mappers.AccountMapper;
import com.oburnett127.accountservice.models.Account;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AccountDao {
    private final SqlSessionFactory sqlSessionFactory;

    public AccountDao(final SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public Account getAccount(final UUID id) {
        try (final var session = sqlSessionFactory.openSession()) {
            final var mapper = session.getMapper(AccountMapper.class);
            final var account = mapper.getAccount(id);
            return account;
        }
    }

    public List<Account> getAll() {
        try (final var session = sqlSessionFactory.openSession()) {
            final var mapper = session.getMapper(AccountMapper.class);
            final var accounts = mapper.getAll();
            return accounts;
        }
    }

    public void save(final Account account) {
        try (final var session = sqlSessionFactory.openSession()) {
            final var mapper = session.getMapper(AccountMapper.class);
            mapper.save(account);
            session.commit();
        }
    }

    public void create(final Account account) {
        try (final var session = sqlSessionFactory.openSession()) {
            final var mapper = session.getMapper(AccountMapper.class);
            mapper.create(account);
            session.commit();
        }
    }
}