package com.oburnett127.transactionservice.model;

import lombok.Data;

import java.math.BigDecimal;
;

@Data
public class DepositRequest {
    private int id;
    private BigDecimal amount;
}