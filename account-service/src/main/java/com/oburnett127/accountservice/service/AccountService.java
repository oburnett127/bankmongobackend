package com.oburnett127.accountservice.service;

import com.oburnett127.accountservice.VO.ResponseTemplateVO;
import com.oburnett127.accountservice.VO.Transaction;
import com.oburnett127.accountservice.model.Account;
import com.oburnett127.accountservice.repository.AccountRepository;
import com.oburnett127.accountservice.util.AccountValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AccountRepository accountRepository;
    private final AccountValidator accountValidator;
    private RestTemplate restTemplate;

    @Autowired
    public AccountService(final AccountRepository accountRepository, final AccountValidator accountValidator,
                            final RestTemplate restTemplate) {
        this.accountRepository = accountRepository;
        this.accountValidator = accountValidator;
        this.restTemplate = restTemplate;
    }

    public AccountService(final AccountRepository accountRepository, final AccountValidator accountValidator) {
        this.accountRepository = accountRepository;
        this.accountValidator = accountValidator;
    }

    @Override
    public List<Account> listAll() {
        return this.accountRepository.findAll();
    }

    @Override
    @SneakyThrows
    public Account getAccount(final int id) {
        final var account = accountRepository.getReferenceById(id);
        return account;
    }

   @Override
   @SneakyThrows
   public ResponseTemplateVO getAccountWithHistory(final int id) {
       ResponseTemplateVO vo = new ResponseTemplateVO();
       final var account = accountRepository.getReferenceById(id);
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
        this.accountRepository.save(account);
    }

    @Override
    @SneakyThrows
    public Account withdraw(int id, BigDecimal amount) {
        final var account = accountRepository.getReferenceById(id);
        accountValidator.withdraw(account, amount);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        return account;
    }

    @Override
    @SneakyThrows
    public Account deposit(int id, BigDecimal amount) {
        final var account = accountRepository.getReferenceById(id);
        accountValidator.deposit(id, amount);
        log.debug("account.getId() {}", account.getId());
        log.debug("account balance: {} amount: {}", account.getBalance(), amount);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return account;
    }

    @Override
    @SneakyThrows
    public Account depositCheck(int id, String fullName, String signature, BigDecimal amount) {
        final var account = accountRepository.getReferenceById(id);
        accountValidator.depositCheck(id, fullName, signature, amount);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return account;
    }

    @Override
    @SneakyThrows
    public Account transfer(int idSender, int idReceiver, BigDecimal amount) {
        final var senderAccount = accountRepository.getReferenceById(idSender);
        final var receiverAccount = accountRepository.getReferenceById(idReceiver);
        accountValidator.transfer(senderAccount, receiverAccount, amount);
        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
        return senderAccount;
    }
}