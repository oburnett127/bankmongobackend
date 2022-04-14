package com.oburnett127.transactionservice.factories;

import com.oburnett127.transactionservice.models.Transaction;
import lombok.Builder;
import org.springframework.stereotype.Component;
import java.security.InvalidParameterException;

@Component
public class TransactionFactory {

    public Transaction get(final String type){
        if ("Base".equals(type)){
            return Transaction.builder().build();
        }
        throw new InvalidParameterException("Invalid Parameter Exception");
    }
}




