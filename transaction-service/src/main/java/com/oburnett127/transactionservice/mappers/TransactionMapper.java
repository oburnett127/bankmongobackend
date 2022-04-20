package com.oburnett127.transactionservice.mappers;

import com.oburnett127.transactionservice.models.Transaction;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
;

public interface TransactionMapper {
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "account", column = "account", javaType = Integer.class),
            @Result(property = "date", column = "date", javaType = Date.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "transType", column = "trans_type", javaType = Integer.class),
            @Result(property = "amount", column = "amount", javaType = BigDecimal.class),
            @Result(property = "sender", column = "sender", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "receiver", column = "receiver", javaType = Integer.class, jdbcType = JdbcType.BIGINT)
    })
    @Select("SELECT id, account, date, description, trans_type, amount, sender, receiver from transaction WHERE id = #{transactionId}")
    Transaction getTransactionById(@Param("transactionId") final Integer transactionId);

    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "account", column = "account", javaType = Integer.class),
            @Result(property = "date", column = "date", javaType = Date.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "transType", column = "trans_type", javaType = Integer.class),
            @Result(property = "amount", column = "amount", javaType = BigDecimal.class),
            @Result(property = "sender", column = "sender", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "receiver", column = "receiver", javaType = Integer.class, jdbcType = JdbcType.BIGINT)
    })
    @Select("SELECT id, account, date, description, trans_type, amount, sender, receiver from transaction WHERE account = #{accountId}")
    List<Transaction> getTransactionsByAccountId(@Param("accountId") final Integer accountId);

    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "account", column = "account", javaType = Integer.class),
            @Result(property = "date", column = "date", javaType = Date.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "transType", column = "trans_type", javaType = Integer.class),
            @Result(property = "amount", column = "amount", javaType = BigDecimal.class),
            @Result(property = "sender", column = "sender", javaType = Integer.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "receiver", column = "receiver", javaType = Integer.class, jdbcType = JdbcType.BIGINT)
    })
    @Select("SELECT id, account, date, description, trans_type, amount, sender, receiver from transaction ORDER BY id DESC")
    List<Transaction> getAll();

    @Update("INSERT INTO transaction (account, date, description, trans_type, amount, sender, receiver) VALUES (#{account}, #{date}, #{description}, #{transType}, #{amount}, #{sender}, #{receiver})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(final Transaction transaction);

//    @Update("UPDATE transaction SET account=#{account}, date=#{date}, description=#{description}, trans_type=#{transType}, amount=#{amount}, sender=#{sender}, receiver=#{receiver}" +
//            " WHERE id = #{id}")
//    void save(final Transaction transaction);
}
