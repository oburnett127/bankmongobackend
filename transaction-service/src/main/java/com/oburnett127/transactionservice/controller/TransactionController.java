package com.oburnett127.transactionservice.controller;

import com.oburnett127.transactionservice.model.*;
import com.oburnett127.transactionservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    private final TransactionService service;

    public TransactionController(final TransactionService service){
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Transaction>> list() {
        final var result = service.listAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/gettran")
    public ResponseEntity<Transaction> getTransactionById(@Validated @RequestBody TransactionRequest transactionRequest) {
        final var result = service.getTransactionById(transactionRequest.getId());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/gettransbyaccount")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@Validated @RequestBody TransactionRequest transactionRequest) {
        final var result = service.getTransactionsByAccountId(transactionRequest.getId());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@Validated @RequestBody CreateTransactionRequest createTransactionRequest) throws IOException {
        final var result = Transaction.builder()
                        .account(createTransactionRequest.getAccount())
                        .date(createTransactionRequest.getDate())
                        .description(createTransactionRequest.getDescription())
                        .transType(createTransactionRequest.getTransType())
                        .amount(createTransactionRequest.getAmount())
                        .sender(createTransactionRequest.getSender())
                        .receiver(createTransactionRequest.getReceiver())
                        .build();

        service.createTransaction(result);
        return ResponseEntity.ok(result);
    }
}