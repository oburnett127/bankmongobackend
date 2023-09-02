package com.oburnett127.transactionservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {
    private int id;
}