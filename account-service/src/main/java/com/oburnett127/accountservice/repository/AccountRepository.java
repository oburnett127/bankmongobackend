package com.oburnett127.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oburnett127.accountservice.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}