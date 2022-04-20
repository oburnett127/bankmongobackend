package com.oburnett127.accountservice.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WithdrawRequest {
    private int id;
    private BigDecimal amount;
}
