package com.oburnett127.accountservice.exceptions;

public class AccountIdMissingException extends RuntimeException{
    public AccountIdMissingException(String message){
        super(message);
    }
}


