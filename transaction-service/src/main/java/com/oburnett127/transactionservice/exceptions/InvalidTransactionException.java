package com.oburnett127.transactionservice.exceptions;

public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String id){
        super("No transaction found with the given id " + id);
    }
}
