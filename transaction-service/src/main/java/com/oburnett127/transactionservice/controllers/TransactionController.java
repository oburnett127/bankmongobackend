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

    @GetMapping("/get")
    public ResponseEntity<Transaction> getTransaction(@Validated @RequestBody TransactionRequest transactionRequest) {
        final var result = service.getTransaction(transactionRequest.getId());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@Validated @RequestBody CreateTransactionRequest createTransactionRequest) throws IOException {
        final TransactionFactory transactionFactory = new TransactionFactory();
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

// I don't think being able to update and delete transactions will be necessary at this point, but
// I'm leaving some code in the controller and service classes in case I decided to implement
//    @PostMapping("/update")
//    public ResponseEntity<Transaction> updateTransaction
//            (@Validated @RequestBody UpdateTransactionRequest updateTransactionRequest)
//            throws IOException {
//        final var id = transactionRequest.getId();
//        final var amount = transactionRequest.getAmount();
//        final var result = service.update(id, amount);
//        return ResponseEntity.ok().body(result);
//    }
}