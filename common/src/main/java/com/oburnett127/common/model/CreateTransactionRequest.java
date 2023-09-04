package com.oburnett127.common.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class CreateTransactionRequest {
    private int account;
    private Date date;
    private String description;
    private int transType;
    private BigDecimal amount;
    private Integer sender;
    private Integer receiver;
}
