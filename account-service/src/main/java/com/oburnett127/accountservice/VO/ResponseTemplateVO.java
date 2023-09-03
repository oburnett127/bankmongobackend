package com.oburnett127.accountservice.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.oburnett127.accountservice.model.Account;
import com.oburnett127.common.model.Transaction;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Account account;
    private List<Transaction> transHistory;
}
