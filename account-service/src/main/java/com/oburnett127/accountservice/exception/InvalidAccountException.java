package com.oburnett127.accountservice.exception;

public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException(String id){
        super("No account found with the given id " + id);
    }
}
