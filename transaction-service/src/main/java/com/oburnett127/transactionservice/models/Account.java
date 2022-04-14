package com.oburnett127.accountservice.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class Account {

	private UUID id;
	private String fullName;
	private BigDecimal balance;
}