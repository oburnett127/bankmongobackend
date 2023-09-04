package com.oburnett127.accountservice.service;

import com.oburnett127.accountservice.VO.ResponseTemplateVO;
import com.oburnett127.accountservice.constant.TransactionType;
import com.oburnett127.accountservice.model.Account;
import com.oburnett127.accountservice.repository.AccountRepository;
import com.oburnett127.accountservice.util.AccountValidator;
import com.oburnett127.common.feign.TransactionServiceClient;
import com.oburnett127.common.model.CreateTransactionRequest;
import com.oburnett127.common.model.Transaction;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AccountService implements AccountOperations {

    private final AccountRepository accountRepository;
    private final AccountValidator accountValidator;
    private final TransactionServiceClient transactionServiceClient;
    

    @Autowired
    public AccountService(final AccountRepository accountRepository, final AccountValidator accountValidator,
                            final TransactionServiceClient transactionServiceClient) {
        this.accountRepository = accountRepository;
        this.accountValidator = accountValidator;
        this.transactionServiceClient = transactionServiceClient;
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
        final CreateTransactionRequest createTransactionRequest = CreateTransactionRequest.builder()
                .account(id)
                .date(new Date())
                .description("withdraw from account " + id)
                .transType(0)
                .amount(amount)
                .sender(null)
                .receiver(null)
                .build();
        transactionServiceClient.createTransaction(createTransactionRequest);
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
        final CreateTransactionRequest createTransactionRequest = CreateTransactionRequest.builder()
                .account(id)
                .date(new Date())
                .description("deposit in account " + id)
                .transType(1)
                .amount(amount)
                .sender(null)
                .receiver(null)
                .build();
        transactionServiceClient.createTransaction(createTransactionRequest);
        return account;
    }

    @Override
    @SneakyThrows
    public Account depositCheck(int id, String fullName, String signature, BigDecimal amount) {
        final var account = accountRepository.getReferenceById(id);
        accountValidator.depositCheck(id, fullName, signature, amount);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        final CreateTransactionRequest createTransactionRequest = CreateTransactionRequest.builder()
                .account(id)
                .date(new Date())
                .description("deposit check in account " + id)
                .transType(2)
                .amount(amount)
                .sender(null)
                .receiver(null)
                .build();
        transactionServiceClient.createTransaction(createTransactionRequest);
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
        final CreateTransactionRequest createTransactionRequest = CreateTransactionRequest.builder()
                .account(idSender)
                .date(new Date())
                .description("transfer from account " + idSender + " to account " + idReceiver)
                .transType(3)
                .amount(amount)
                .sender(idSender)
                .receiver(idReceiver)
                .build();
        transactionServiceClient.createTransaction(createTransactionRequest);
        return senderAccount;
    }

    @Override
    public ResponseTemplateVO getAccountWithHistory(int id) {
        final Account account = accountRepository.getReferenceById(id);
        
        ResponseEntity<List<Transaction>> transactions = transactionServiceClient.getTransactionsByAccountId(id);
        ResponseTemplateVO vo = ResponseTemplateVO.builder()
                                    .account(account)
                                    .transHistory(transactions.getBody())
                                    .build();
        return vo;
    }
}