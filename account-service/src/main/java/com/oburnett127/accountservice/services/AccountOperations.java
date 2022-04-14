package com.oburnett127.accountservice.services;

import com.oburnett127.accountservice.models.Account;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountOperations {
    List<Account> listAll();
    void createAccount(Account account);
    Account getAccount(UUID id);
    Account withdraw(UUID id, BigDecimal amount);
    Account deposit(UUID id, BigDecimal amount);
    Account depositCheck(UUID id, String fullName, String signature, BigDecimal amount);
    public Account transfer(UUID idSender, UUID idReceiver, BigDecimal amount);
}
