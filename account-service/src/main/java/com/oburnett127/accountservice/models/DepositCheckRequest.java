package com.oburnett127.accountservice.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class DepositCheckRequest {
    private UUID id;
    private String fullName;
    private String signature;
    private BigDecimal amount;
}