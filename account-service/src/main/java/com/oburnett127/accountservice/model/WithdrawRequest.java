package com.oburnett127.accountservice.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WithdrawRequest {
    private int id;
    private BigDecimal amount;
}
