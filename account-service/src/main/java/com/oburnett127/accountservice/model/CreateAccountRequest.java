package com.oburnett127.accountservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    private String fullName;
    private BigDecimal balance;
}
