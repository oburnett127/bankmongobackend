package com.oburnett127.accountservice.models;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class TransferRequest {
    private int idSender;
    private int idReceiver;
    private BigDecimal amount;
}