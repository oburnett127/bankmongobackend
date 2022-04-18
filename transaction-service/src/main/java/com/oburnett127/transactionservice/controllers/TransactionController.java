package com.oburnett127.transactionservice.controllers;

import com.oburnett127.transactionservice.factories.TransactionFactory;
import com.oburnett127.transactionservice.models.*;
import com.oburnett127.transactionservice.services.TransactionService;
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

//    @GetMapping("/transaction")
//    public ResponseEntity<Transaction> getTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
//        final var transaction = service.getTransaction(transactionRequest.getId());
//        return ResponseEntity.ok().body(transaction);
//    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@Validated @RequestBody CreateTransactionRequest createTransactionRequest) throws IOException {
        final TransactionFactory transactionFactory = new TransactionFactory();
        final var transaction = Transaction.builder()
                        .account(createTransactionRequest.getAccount())
                        .date(createTransactionRequest.getDate())
                        .description(createTransactionRequest.getDescription())
                        .transType(createTransactionRequest.getTransType())
                        .amount(createTransactionRequest.getAmount())
                        .sender(createTransactionRequest.getSender())
                        .receiver(createTransactionRequest.getReceiver())
                        .build();

        service.createTransaction(transaction);
        return ResponseEntity.ok(transaction);
    }

//    @PostMapping("/update")
//    public ResponseEntity<Transaction> updateTransaction
//            (@Validated @RequestBody UpdateTransactionRequest updateTransactionRequest)
//            throws IOException {
//        final var id = transactionReqest.getId();
//        final var amount = transactionRequest.getAmount();
//        final var result = service.update(id, amount);
//        return ResponseEntity.ok().body(result);
//    }
}