package com.oburnett127.accountservice.utils;

import com.oburnett127.accountservice.constants.ErrorMessage;
import com.oburnett127.accountservice.constants.TransactionType;
import com.oburnett127.accountservice.exceptions.InsufficientFundsException;
import com.oburnett127.accountservice.exceptions.InsufficientWithdrawException;
import com.oburnett127.accountservice.exceptions.InvalidOperationException;
import com.oburnett127.accountservice.exceptions.NameCheckException;
import com.oburnett127.accountservice.exceptions.SignatureCheckException;
import com.oburnett127.accountservice.exceptions.SignatureMismatchException;
import com.oburnett127.accountservice.exceptions.ZeroDepositException;
import com.oburnett127.accountservice.exceptions.ZeroWithdrawException;
import com.oburnett127.accountservice.models.Account;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.function.Predicate;

@Component
@Slf4j
public class AccountValidator {

    @SneakyThrows
    public void withdraw(Account account, BigDecimal amount) {
        validateTransactionAmount(amount, TransactionType.WITHDRAW);

        //Check to see if balance is less than requested withdraw amt
        if (account.getBalance().compareTo(amount) < 0) {
            log.error(ErrorMessage.MSG9);
            throw new InsufficientWithdrawException();
        }
    }

    @SneakyThrows
    public void deposit(UUID id, BigDecimal amount) {
        validateTransactionAmount(amount, TransactionType.DEPOSIT);
    }

    @SneakyThrows
    public void depositCheck(UUID id, String fullName, String signature, BigDecimal amount) {
        validateTransactionAmount(amount, TransactionType.DEPOSIT);

        if (StringUtils.isBlank(fullName)) {
            log.error(ErrorMessage.MSG5);
            throw new NameCheckException();
        }

        if (!StringUtils.isAlphaSpace(fullName)) {
            log.error(ErrorMessage.MSG6, fullName);
            throw new NameCheckException();
        }

        if (StringUtils.isBlank(signature)) {
            log.error(ErrorMessage.MSG3);
            throw new SignatureCheckException();
        }

        if (!StringUtils.isAlphaSpace(signature)) {
            log.error(ErrorMessage.MSG4, signature);
            throw new SignatureCheckException();
        }

        if(!fullName.equals(signature)) {
            throw new SignatureMismatchException();
        }
    }

    @SneakyThrows
    public void transfer(Account senderAccount, Account receiverAccount, BigDecimal amount) {
        if (senderAccount.getId().compareTo(receiverAccount.getId()) == 0) {
            throw new InvalidOperationException();
        }

        if (senderAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException();
        }

        validateTransactionAmount(amount, TransactionType.DEPOSIT);
    }

    void validateTransactionAmount(BigDecimal amount, TransactionType transaction) {
        final Predicate<BigDecimal> isZero = (a) -> a == null || BigDecimal.ZERO.compareTo(a) >= 0;

        //Check to see if amount is 0, negative or null
        if (isZero.test(amount)) {
            log.error(ErrorMessage.MSG2);

            if(transaction.equals(TransactionType.WITHDRAW)) {
                throw new ZeroWithdrawException();
            } else if (transaction.equals(TransactionType.DEPOSIT)) {
                throw new ZeroDepositException();
            } else {
                throw new InvalidOperationException();
            }
        }
    }
}
