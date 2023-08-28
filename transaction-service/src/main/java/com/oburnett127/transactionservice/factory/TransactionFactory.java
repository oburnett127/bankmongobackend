package com.oburnett127.transactionservice.factory;

import lombok.Builder;
import org.springframework.stereotype.Component;

import com.oburnett127.transactionservice.model.Transaction;

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




