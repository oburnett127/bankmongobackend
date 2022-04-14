package com.oburnett127.accountservice.models;

import com.oburnett127.accountservice.constants.TransactionType;
import com.oburnett127.accountservice.constants.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Transaction {
    private int id;
    private Date date;
    private String description;
    private TransactionType transType;
    private BigDecimal amount;
    private BigDecimal balanceRemaining;
    private UUID sender;
    private UUID receiver;
}