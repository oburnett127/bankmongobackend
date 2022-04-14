package com.oburnett127.transactionservice.mappers;

import com.oburnett127.transactionservice.models.Transaction;
import com.oburnett127.transactionservice.typehandlers.UuidTypeHandler;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionMapper {
    @Results({
            @Result(property = "id", column = "id", javaType = UUID.class, typeHandler = UuidTypeHandler.class),
            @Result(property = "fullName", column = "full_name", javaType = String.class),
            @Result(property = "balance", column = "balance", javaType = BigDecimal.class)
    })
    @Select("SELECT id, full_name, balance from transaction WHERE id = #{TransactionId}")
    Transaction getTransaction(@Param("transactionId") final UUID transactionId);

    @Results({
            @Result(property = "id", column = "id", javaType = UUID.class, typeHandler = UuidTypeHandler.class),
            @Result(property = "fullName", column = "full_name", javaType = String.class),
            @Result(property = "balance", column = "balance", javaType = BigDecimal.class)
    })
    @Select("SELECT id, full_name, balance from transaction ORDER BY id DESC")
    List<Transaction> getAll();

    @Update("UPDATE transaction SET full_name=#{fullName}, balance=#{balance}" +
            " WHERE id = #{id,typeHandler=com.oburnett127.transactionservice.typehandlers.UuidTypeHandler}")
    void save(final Transaction transaction);

    @Update("INSERT INTO transaction (full_name, balance) VALUES (#{fullName}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(final Transaction transaction);
}
