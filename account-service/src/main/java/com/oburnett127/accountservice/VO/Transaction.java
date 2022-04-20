package com.oburnett127.accountservice.VO;

import com.oburnett127.accountservice.constants.TransactionType;
import com.oburnett127.accountservice.constants.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@Builder
public class Transaction {
    private int id;
    private Date date;
    private String description;
    private TransactionType transType;
    private BigDecimal amount;
    private BigDecimal balanceRemaining;
    private int sender;
    private int receiver;
}