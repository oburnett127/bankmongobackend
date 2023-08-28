package com.oburnett127.accountservice.model;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class TransferRequest {
    private int idSender;
    private int idReceiver;
    private BigDecimal amount;
}