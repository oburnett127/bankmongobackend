package com.oburnett127.transactionservice.model;

import lombok.Data;

import java.math.BigDecimal;
;

@Data
public class DepositCheckRequest {
    private int id;
    private String fullName;
    private String signature;
    private BigDecimal amount;
}