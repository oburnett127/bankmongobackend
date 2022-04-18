package com.oburnett127.transactionservice.models;

import lombok.Builder;
import lombok.Data;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int account;
    private Date date;
    private String description;
    private int transType;
    private BigDecimal amount;
    private Integer sender;
    private Integer receiver;
}