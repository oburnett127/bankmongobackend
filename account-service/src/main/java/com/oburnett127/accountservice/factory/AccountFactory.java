package com.oburnett127.accountservice.factory;

import org.springframework.stereotype.Component;

import com.oburnett127.accountservice.model.Account;

import java.security.InvalidParameterException;

@Component
public class AccountFactory {

    public Account get(final String type){
        if ("Base".equals(type)){
            return Account.builder().build();
        }
        throw new InvalidParameterException("Invalid Parameter Exception");
    }
}




