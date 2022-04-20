package com.oburnett127.accountservice.VO;

import com.oburnett127.accountservice.models.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Account account;
    private List<Transaction> transHistory;
}
