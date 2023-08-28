package com.oburnett127.accountservice.controller;

import com.oburnett127.accountservice.VO.ResponseTemplateVO;
import com.oburnett127.accountservice.constant.DebugMessage;
import com.oburnett127.accountservice.model.Account;
import com.oburnett127.accountservice.model.AccountRequest;
import com.oburnett127.accountservice.model.CreateAccountRequest;
import com.oburnett127.accountservice.model.DepositCheckRequest;
import com.oburnett127.accountservice.model.DepositRequest;
import com.oburnett127.accountservice.model.TransferRequest;
import com.oburnett127.accountservice.model.WithdrawRequest;
import com.oburnett127.accountservice.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    private final AccountService service;

    public AccountController(final AccountService service){
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Account>> view() {
        final var result = service.listAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get")
    public ResponseEntity<Account> getAccount(@Validated @RequestBody AccountRequest accountRequest) {
        final var account = service.getAccount(accountRequest.getId());
        return ResponseEntity.ok().body(account);
    }

   @GetMapping("/gethistory")
   public ResponseEntity<ResponseTemplateVO> getAccountWithHistory(@Validated @RequestBody AccountRequest accountRequest) {
       final ResponseTemplateVO result = service.getAccountWithHistory(accountRequest.getId());
       return ResponseEntity.ok().body(result);
   }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@Validated @RequestBody CreateAccountRequest createAccountRequest) throws IOException {
//        System.out.println(createAccountRequest.getFullName());
//        System.out.println(createAccountRequest.getBalance());
        final var account = Account.builder()
                .fullName(createAccountRequest.getFullName())
                .balance(createAccountRequest.getBalance())
                .build();
        service.createAccount(account);
        log.debug(DebugMessage.MSG5,account.getFullName(),account.getId());
        return ResponseEntity.ok(account);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> makeWithdraw(@Validated @RequestBody WithdrawRequest withdrawRequest) throws IOException {
        final var id = withdrawRequest.getId();
        final var amount = withdrawRequest.getAmount();
        final var result = service.withdraw(id, amount);
        log.debug(DebugMessage.MSG6, amount, result.getId(), result.getBalance());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> makeDeposit(@Validated @RequestBody DepositRequest depositRequest) {
        final var id = depositRequest.getId();
        final var amount = depositRequest.getAmount();
        final var result = service.deposit(id, amount);

        log.debug(DebugMessage.MSG1, result.getId());

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/depositcheck")
    public ResponseEntity<Account> depositCheck(@Validated @RequestBody DepositCheckRequest depositCheckRequest) {
        final var id = depositCheckRequest.getId();
        final var fullName = depositCheckRequest.getFullName();
        final var signature = depositCheckRequest.getSignature();
        final var amount = depositCheckRequest.getAmount();
        final var result = service.depositCheck(id, fullName, signature, amount);
        log.debug(DebugMessage.MSG2, result.getId());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Account> transfer(@Validated @RequestBody TransferRequest transferRequest) {
        final var idSender = transferRequest.getIdSender();
        final var idReceiver = transferRequest.getIdReceiver();
        final var amount = transferRequest.getAmount();
        final var result = service.transfer(idSender, idReceiver, amount);
        log.debug(DebugMessage.MSG7, result.getId());
        return ResponseEntity.ok().body(result);
    }
}