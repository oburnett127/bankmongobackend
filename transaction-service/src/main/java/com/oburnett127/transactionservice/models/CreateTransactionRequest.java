package com.oburnett127.transactionservice.models;

import com.oburnett127.transactionservice.constants.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class CreateTransactionRequest {
    private int id;
    private UUID account;
    private Date date;
    private String description;
    private TransactionType transType;
    private BigDecimal amount;
    private BigDecimal balanceRemaining;
    private UUID sender;
    private UUID receiver;
}
