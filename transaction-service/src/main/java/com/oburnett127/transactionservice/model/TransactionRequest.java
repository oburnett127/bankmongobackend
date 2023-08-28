package com.oburnett127.transactionservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import com.oburnett127.transactionservice.constant.TransactionType;

@Data
public class TransactionRequest {
    private int id;
}