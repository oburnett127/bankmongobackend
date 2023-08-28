package com.oburnett127.transactionservice.util;

import com.oburnett127.transactionservice.constant.ErrorMessage;
import com.oburnett127.transactionservice.constant.TransactionType;
import com.oburnett127.transactionservice.exception.InsufficientFundsException;
import com.oburnett127.transactionservice.exception.InsufficientWithdrawException;
import com.oburnett127.transactionservice.exception.InvalidOperationException;
import com.oburnett127.transactionservice.exception.NameCheckException;
import com.oburnett127.transactionservice.exception.SignatureCheckException;
import com.oburnett127.transactionservice.exception.SignatureMismatchException;
import com.oburnett127.transactionservice.exception.ZeroDepositException;
import com.oburnett127.transactionservice.exception.ZeroWithdrawException;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Predicate;

@Component
@Slf4j
public class TransactionValidator {

//    @SneakyThrows
//    public void withdraw(Transaction Transaction, BigDecimal amount) {
//        validateTransactionAmount(amount, TransactionType.WITHDRAW);
//
//        //Check to see if balance is less than requested withdraw amt
//        if (Transaction.getBalance().compareTo(amount) < 0) {
//            log.error(ErrorMessage.MSG9);
//            throw new InsufficientWithdrawException();
//        }
//    }
//
//    @SneakyThrows
//    public void deposit(int id, BigDecimal amount) {
//        validateTransactionAmount(amount, TransactionType.DEPOSIT);
//    }
//
//    @SneakyThrows
//    public void depositCheck(int id, String fullName, String signature, BigDecimal amount) {
//        validateTransactionAmount(amount, TransactionType.DEPOSIT);
//
//        if (StringUtils.isBlank(fullName)) {
//            log.error(ErrorMessage.MSG5);
//            throw new NameCheckException();
//        }
//
//        if (!StringUtils.isAlphaSpace(fullName)) {
//            log.error(ErrorMessage.MSG6, fullName);
//            throw new NameCheckException();
//        }
//
//        if (StringUtils.isBlank(signature)) {
//            log.error(ErrorMessage.MSG3);
//            throw new SignatureCheckException();
//        }
//
//        if (!StringUtils.isAlphaSpace(signature)) {
//            log.error(ErrorMessage.MSG4, signature);
//            throw new SignatureCheckException();
//        }
//
//        if(!fullName.equals(signature)) {
//            throw new SignatureMismatchException();
//        }
//    }
//
//    @SneakyThrows
//    public void transfer(Transaction senderTransaction, Transaction receiverTransaction, BigDecimal amount) {
//        if (senderTransaction.getId().compareTo(receiverTransaction.getId()) == 0) {
//            throw new InvalidOperationException();
//        }
//
//        if (senderTransaction.getBalance().compareTo(amount) < 0) {
//            throw new InsufficientFundsException();
//        }
//
//        validateTransactionAmount(amount, TransactionType.DEPOSIT);
//    }

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
