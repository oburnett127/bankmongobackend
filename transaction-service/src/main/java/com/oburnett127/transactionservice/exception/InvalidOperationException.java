package com.oburnett127.transactionservice.exception;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(){
        super("The operation you are trying to perform is invalid.");
    }
}

