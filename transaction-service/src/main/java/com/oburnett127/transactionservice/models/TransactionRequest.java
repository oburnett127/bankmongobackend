package com.oburnett127.transactionservice.models;

import com.oburnett127.transactionservice.constants.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionRequest {
    private int id;
}