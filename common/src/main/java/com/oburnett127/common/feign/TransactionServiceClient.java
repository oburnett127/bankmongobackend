package com.oburnett127.common.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import com.oburnett127.common.model.CreateTransactionRequest;
import com.oburnett127.common.model.Transaction;

@FeignClient(name = "transaction-service")
public interface TransactionServiceClient {

    @GetMapping("/transaction/gettransbyaccount")
    ResponseEntity<List<Transaction>> getTransactionsByAccountId(@RequestParam("id") int id);
    
    @PostMapping("/transaction/create")
    ResponseEntity<Transaction> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest);

}
