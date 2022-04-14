package com.oburnett127.accountservice.factories;

import com.oburnett127.accountservice.models.Account;
import org.springframework.stereotype.Component;
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




