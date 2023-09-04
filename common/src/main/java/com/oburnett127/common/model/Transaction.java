package com.oburnett127.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Basic(optional = false)
    private int account;
    @Basic(optional = false)
    private Date date;
    @Basic(optional = false)
    private String description;
    @Basic(optional = false)
    private int transType;
    @Basic(optional = false)
    private BigDecimal amount;
    @Basic(optional = false)
    private Integer sender;
    @Basic(optional = false)
    private Integer receiver;
}