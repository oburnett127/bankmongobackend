package com.oburnett127.transactionservice.models;

import com.oburnett127.transactionservice.constants.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
;

@Data
public class CreateTransactionRequest {
    private int id;
    private int account;
    private Date date;
    private String description;
    private int transType;
    private BigDecimal amount;
    private Integer sender;
    private Integer receiver;
}
