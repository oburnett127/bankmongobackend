package com.oburnett127.transactionservice.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.oburnett127.transactionservice.model.Transaction;
import com.oburnett127.transactionservice.model.TransactionRequest;

@FeignClient(name = "transaction-service")
public interface TransactionServiceClient {

    @GetMapping("/transaction/gettransbyaccount")
    ResponseEntity<List<Transaction>> getTransactionsByAccountId(@RequestBody TransactionRequest transactionRequest);
    
}
