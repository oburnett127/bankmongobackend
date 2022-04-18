package com.oburnett127.transactionservice.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
;

@Data
@Builder
public class Account {

	private Integer account;
	private String fullName;
	private BigDecimal balance;
}