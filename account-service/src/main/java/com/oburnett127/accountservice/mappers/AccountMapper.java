package com.oburnett127.accountservice.mappers;

import com.oburnett127.accountservice.models.Account;
import com.oburnett127.accountservice.typehandlers.UuidTypeHandler;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountMapper {
    @Results({
            @Result(property = "id", column = "id", javaType = UUID.class, typeHandler = UuidTypeHandler.class),
            @Result(property = "fullName", column = "full_name", javaType = String.class),
            @Result(property = "balance", column = "balance", javaType = BigDecimal.class)
    })
    @Select("SELECT id, full_name, balance from account WHERE id = #{accountId}")
    Account getAccount(@Param("accountId") final UUID accountId);

    @Results({
            @Result(property = "id", column = "id", javaType = UUID.class, typeHandler = UuidTypeHandler.class),
            @Result(property = "fullName", column = "full_name", javaType = String.class),
            @Result(property = "balance", column = "balance", javaType = BigDecimal.class)
    })
    @Select("SELECT id, full_name, balance from account ORDER BY id DESC")
    List<Account> getAll();

    @Update("UPDATE account SET full_name=#{fullName}, balance=#{balance}" +
            " WHERE id = #{id,typeHandler=com.oburnett127.accountservice.typehandlers.UuidTypeHandler}")
    void save(final Account account);

    @Update("INSERT INTO account (full_name, balance) VALUES (#{fullName}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(final Account account);
}
