package com.oburnett127.accountservice.mappers;

import com.oburnett127.accountservice.models.Account;
import org.apache.ibatis.annotations.*;
import java.math.BigDecimal;
import java.util.List;


public interface AccountMapper {
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "fullName", column = "full_name", javaType = String.class),
            @Result(property = "balance", column = "balance", javaType = BigDecimal.class)
    })
    @Select("SELECT id, full_name, balance from account WHERE id = #{accountId}")
    Account getAccount(@Param("accountId") final int accountId);

    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "fullName", column = "full_name", javaType = String.class),
            @Result(property = "balance", column = "balance", javaType = BigDecimal.class)
    })
    @Select("SELECT id, full_name, balance from account ORDER BY id DESC")
    List<Account> getAll();

    @Update("UPDATE account SET full_name=#{fullName}, balance=#{balance}" +
            " WHERE id = #{id}")
    void save(final Account account);

    @Update("INSERT INTO account (full_name, balance) VALUES (#{fullName}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(final Account account);
}
