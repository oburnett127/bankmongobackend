package com.oburnett127.accountservice.services;

import com.oburnett127.accountservice.VO.ResponseTemplateVO;
import com.oburnett127.accountservice.VO.Transaction;
import com.oburnett127.accountservice.daos.AccountDao;
import com.oburnett127.accountservice.models.Account;
import com.oburnett127.accountservice.utils.AccountValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;


@Service
@Slf4j
public class AccountService implements AccountOperations {

    private final AccountDao accountDao;
    private final AccountValidator accountValidator;
    private RestTemplate restTemplate;

   public AccountService(final AccountDao accountDao, final AccountValidator accountValidator,
                         final RestTemplate restTemplate) {
       this.accountDao = accountDao;
       this.accountValidator = accountValidator;
       this.restTemplate = restTemplate;
   }

    public AccountService(final AccountDao accountDao, final AccountValidator accountValidator) {
        this.accountDao = accountDao;
        this.accountValidator = accountValidator;
    }

    @Override
    public List<Account> listAll() {
        return this.accountDao.getAll();
    }

    @Override
    @SneakyThrows
    public Account getAccount(final int id) {
        final var account = accountDao.getAccount(id);
        return account;
    }

   @Override
   @SneakyThrows
   public ResponseTemplateVO getAccountWithHistory(final int id) {
       ResponseTemplateVO vo = new ResponseTemplateVO();
       final var account = accountDao.getAccount(id);
       String url = UriComponentsBuilder.fromHttpUrl("http://transaction-service/gettransbyaccount/{id}")
        .buildAndExpand(id)
        .toUriString();

        ResponseEntity<List<Transaction>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Transaction>>() {}
        );

        List<Transaction> transHistory = response.getBody();

       vo.setAccount(account);
       vo.setTransHistory(transHistory);
       return vo;
   }

    @Override
    public void createAccount(Account account) {
        this.accountDao.create(account);
    }

    @Override
    @SneakyThrows
    public Account withdraw(int id, BigDecimal amount) {
        final var account = accountDao.getAccount(id);
        accountValidator.withdraw(account, amount);
        account.setBalance(account.getBalance().subtract(amount));
        accountDao.save(account);
        return account;
    }

    @Override
    @SneakyThrows
    public Account deposit(int id, BigDecimal amount) {
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
    public Account depositCheck(int id, String fullName, String signature, BigDecimal amount) {
        final var account = accountDao.getAccount(id);
        accountValidator.depositCheck(id, fullName, signature, amount);
        account.setBalance(account.getBalance().add(amount));
        accountDao.save(account);
        return account;
    }

    @Override
    @SneakyThrows
    public Account transfer(int idSender, int idReceiver, BigDecimal amount) {
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