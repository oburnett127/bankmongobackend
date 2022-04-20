package com.oburnett127.accountservice.services;

import com.oburnett127.accountservice.VO.ResponseTemplateVO;
import com.oburnett127.accountservice.models.Account;
import java.math.BigDecimal;
import java.util.List;


public interface AccountOperations {
    List<Account> listAll();
    Account getAccount(int id);
    //ResponseTemplateVO getAccountWithHistory(int id);
    void createAccount(Account account);
    Account withdraw(int id, BigDecimal amount);
    Account deposit(int id, BigDecimal amount);
    Account depositCheck(int id, String fullName, String signature, BigDecimal amount);
    public Account transfer(int idSender, int idReceiver, BigDecimal amount);
}
