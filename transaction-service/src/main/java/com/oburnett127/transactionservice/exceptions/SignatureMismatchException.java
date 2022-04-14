package com.oburnett127.transactionservice.exceptions;

public class SignatureMismatchException extends RuntimeException {
    public SignatureMismatchException(){
        super("The full name and the signature do not match.");
    }

}
