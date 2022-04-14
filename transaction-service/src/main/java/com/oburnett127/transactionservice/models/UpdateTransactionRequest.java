package com.oburnett127.transactionservice.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UpdateTransactionRequest {
    private UUID id;
    private BigDecimal amount;
}
