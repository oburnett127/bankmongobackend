package com.oburnett127.transactionservice.exception;

public class SignatureCheckException extends RuntimeException{
    public SignatureCheckException(){
        super("The check must have a signature and the signature cannot contains digits");
    }
}