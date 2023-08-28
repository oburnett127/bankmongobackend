package com.oburnett127.transactionservice.exception;

public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String id){
        super("No transaction found with the given id " + id);
    }
}
