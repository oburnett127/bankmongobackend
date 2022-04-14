package com.oburnett127.accountservice.services;

import com.oburnett127.accountservice.daos.AccountDao;
import com.oburnett127.accountservice.models.Account;
import com.oburnett127.accountservice.utils.AccountValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AccountService implements AccountOperations {

    private final AccountDao accountDao;
    private final AccountValidator accountValidator;

    public AccountService(final AccountDao accountDao, final AccountValidator accountValidator) {
        this.accountDao = accountDao;
        this.accountValidator = accountValidator;
    }

    @Override
    public List<Account> listAll() {
        return this.accountDao.getAll();
    }

    @Override
    public void createAccount(Account account) {
        this.accountDao.create(account);
    }

    @Override
    @SneakyThrows
    public Account getAccount(final UUID id) {
        final var account = accountDao.getAccount(id);

        return account;
    }

    @Override
    @SneakyThrows
    public Account withdraw(UUID id, BigDecimal amount) {
        final var account = accountDao.getAccount(id);

        accountValidator.withdraw(account, amount);

        account.setBalance(account.getBalance().subtract(amount));

        accountDao.save(account);

        return account;
    }

    @Override
    @SneakyThrows
    public Account deposit(UUID id, BigDecimal amount) {
        final var account = accountDao.getAccount(id);

        accountValidator.deposit(id, amount);

        log.debug("account.getId() {}", account.getId());
        log.debug("account balance: {} amount: {}", account.getBalance(), amount);

        account.setBalance(account.getBalance().add(amount));

        accountDao.save(account);

        return account;
    }

    @Override
    @SneakyThrows
    public Account depositCheck(UUID id, String fullName, String signature, BigDecimal amount) {
        final var account = accountDao.getAccount(id);

        accountValidator.depositCheck(id, fullName, signature, amount);
      
        account.setBalance(account.getBalance().add(amount));

        accountDao.save(account);

        return account;
    }

    @Override
    @SneakyThrows
    public Account transfer(UUID idSender, UUID idReceiver, BigDecimal amount) {
        final var senderAccount = accountDao.getAccount(idSender);
        final var receiverAccount = accountDao.getAccount(idReceiver);

        accountValidator.transfer(senderAccount, receiverAccount, amount);

        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));

        accountDao.save(senderAccount);
        accountDao.save(receiverAccount);

        return senderAccount;
    }
}