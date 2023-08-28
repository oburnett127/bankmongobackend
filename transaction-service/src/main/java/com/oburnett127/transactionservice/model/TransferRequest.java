package com.oburnett127.transactionservice.model;

import lombok.Data;

import java.math.BigDecimal;
;

@Data
public class TransferRequest {
    private Integer idSender;
    private Integer idReceiver;
    private BigDecimal amount;
}