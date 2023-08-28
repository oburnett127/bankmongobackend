package com.oburnett127.accountservice.exception;

public class AccountIdMissingException extends RuntimeException{
    public AccountIdMissingException(String message){
        super(message);
    }
}


