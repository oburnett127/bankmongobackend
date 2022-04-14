package com.oburnett127.transactionservice.models;

import com.oburnett127.transactionservice.constants.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.chrono.Chronology;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Transaction {
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