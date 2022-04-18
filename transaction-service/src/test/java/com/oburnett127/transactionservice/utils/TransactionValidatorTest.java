//package com.oburnett127.transactionservice.utils;
//
//
//import com.flextrade.jfixture.JFixture;
//import com.oburnett127.transactionservice.constants.TransactionType;
//import com.oburnett127.transactionservice.exceptions.InsufficientFundsException;
//import com.oburnett127.transactionservice.exceptions.InsufficientWithdrawException;
//import com.oburnett127.transactionservice.exceptions.InvalidOperationException;
//import com.oburnett127.transactionservice.exceptions.NameCheckException;
//import com.oburnett127.transactionservice.exceptions.SignatureCheckException;
//import com.oburnett127.transactionservice.exceptions.SignatureMismatchException;
//import com.oburnett127.transactionservice.exceptions.ZeroDepositException;
//import com.oburnett127.transactionservice.exceptions.ZeroWithdrawException;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//
//import java.math.BigDecimal;
//;
//import java.util.function.Predicate;
//import java.util.stream.Stream;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//public class TransactionValidatorTest {
//
//    @InjectMocks
//    private TransactionValidator TransactionValidator;
//
//    private static Stream<Arguments> withdrawParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        // id | amount | Transaction
//        return Stream.of(
//            Arguments.of(Transaction, Transaction.getBalance()),
//            Arguments.of(Transaction, Transaction.getBalance().add(BigDecimal.ONE)),
//            Arguments.of(Transaction, Transaction.getBalance().negate()),
//            Arguments.of(Transaction, BigDecimal.ZERO)
//        );
//    }
//
//    private static Stream<Arguments> depositParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        // id | amount | Transaction
//        return Stream.of(
//                Arguments.of(Transaction.getId(), Transaction.getBalance()),
//                Arguments.of(null, Transaction.getBalance()),
//                Arguments.of(Transaction.getId(), Transaction.getBalance().negate()),
//                Arguments.of(null, Transaction.getBalance().negate()),
//                Arguments.of(Transaction.getId(), BigDecimal.ZERO),
//                Arguments.of(null, BigDecimal.ZERO)
//        );
//    }
//
//    private static Stream<Arguments> depositCheckParams() {
//        final var int = int.randomint();
//        final var fullName = "Bobby Drillboids";
//
//        // id | amount | Transaction
//        return Stream.of(
//                Arguments.of(int, fullName, fullName, new BigDecimal("100.00")),
//                Arguments.of(null, fullName, fullName, new BigDecimal("100.00")),
//                Arguments.of(int, fullName, fullName, null),
//                Arguments.of(int, null, fullName, new BigDecimal("100.00")),
//                Arguments.of(int, fullName, null, new BigDecimal("100.00")),
//                Arguments.of(int, fullName, fullName, BigDecimal.ONE),
//                Arguments.of(null, fullName, fullName, BigDecimal.ONE),
//                Arguments.of(int, null, fullName, BigDecimal.ONE),
//                Arguments.of(int, fullName, null, BigDecimal.ONE),
//                Arguments.of(int, fullName, fullName, BigDecimal.ONE.negate()),
//                Arguments.of(null, fullName, fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int, null, fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int, fullName, null, BigDecimal.ONE.negate()),
//                Arguments.of(int, fullName, fullName, BigDecimal.ZERO),
//                Arguments.of(null, fullName, fullName, BigDecimal.ZERO),
//                Arguments.of(int, null, fullName, BigDecimal.ZERO),
//                Arguments.of(int, fullName, null, BigDecimal.ZERO),
//                Arguments.of(int, "", fullName, new BigDecimal("100.00")),
//                Arguments.of(null, "", fullName, new BigDecimal("100.00")),
//                Arguments.of(int, "", null, new BigDecimal("100.00")),
//                Arguments.of(int, "", fullName, BigDecimal.ONE),
//                Arguments.of(int, "", fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int, "", fullName, BigDecimal.ZERO),
//                Arguments.of(null, "", fullName, BigDecimal.ONE),
//                Arguments.of(int, "", null, BigDecimal.ONE),
//                Arguments.of(null, "", fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int, "", null, BigDecimal.ONE),
//                Arguments.of(null, fullName + "123", fullName, new BigDecimal("100.00")),
//                Arguments.of(int, fullName + "123", null, new BigDecimal("100.00")),
//                Arguments.of(int, fullName + "123", fullName, BigDecimal.ONE),
//                Arguments.of(null, fullName + "123", fullName, BigDecimal.ONE),
//                Arguments.of(int, fullName + "123", null, BigDecimal.ONE),
//                Arguments.of(int, fullName + "123", fullName, BigDecimal.ONE.negate()),
//                Arguments.of(null, fullName + "123", fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int, fullName + "123", null, BigDecimal.ONE.negate()),
//                Arguments.of(int, fullName + "123", fullName, BigDecimal.ZERO),
//                Arguments.of(null, fullName + "123", fullName, BigDecimal.ZERO),
//                Arguments.of(int, fullName + "123", null, BigDecimal.ZERO),
//                Arguments.of(int, fullName + "123", null, BigDecimal.ONE),
//                Arguments.of(int, fullName + "123", null, BigDecimal.ONE.negate()),
//                Arguments.of(int, fullName, fullName + "$123", new BigDecimal("100.00")),
//                Arguments.of(null, fullName, fullName + "$123", new BigDecimal("100.00")),
//                Arguments.of(int, null, fullName + "$123", new BigDecimal("100.00")),
//                Arguments.of(int, fullName, fullName + "$123", BigDecimal.ZERO),
//                Arguments.of(int, fullName, fullName + "$123", BigDecimal.ONE),
//                Arguments.of(int, fullName, fullName + "$123", BigDecimal.ONE.negate()),
//                Arguments.of(int, fullName + "abcdefg", fullName, new BigDecimal("100.00")),
//                Arguments.of(null, fullName + "abcdefg", fullName, new BigDecimal("100.00")),
//                Arguments.of(int, fullName + "abcdefg", null, new BigDecimal("100.00")),
//                Arguments.of(int, fullName + "abcdefg", fullName, BigDecimal.ZERO),
//                Arguments.of(int, fullName + "abcdefg", fullName, BigDecimal.ONE),
//                Arguments.of(int, fullName + "abcdefg", fullName, BigDecimal.ONE.negate())
//        );
//    }
//
//    private static Stream<Arguments> transferParams() {
//        final var jfixture = new JFixture();
//        final var sender = jfixture.create(Transaction.class);
//        final var receiver = jfixture.create(Transaction.class);
//
//        // id | amount | Transaction
//        return Stream.of(
//                Arguments.of(sender, receiver, sender.getBalance()),
//                Arguments.of(sender, sender, sender.getBalance()),
//                Arguments.of(sender, sender, sender.getBalance().negate()),
//                Arguments.of(sender, sender, sender.getBalance().add(BigDecimal.ONE)),
//                Arguments.of(sender, sender, BigDecimal.ZERO),
//                Arguments.of(sender, receiver, sender.getBalance().negate()),
//                Arguments.of(sender, receiver, sender.getBalance().add(BigDecimal.ONE)),
//                Arguments.of(sender, receiver, BigDecimal.ZERO)
//        );
//    }
//
//    private static Stream<Arguments> validateAmountParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        return Stream.of(
//                Arguments.of(Transaction.getBalance(), TransactionType.WITHDRAW),
//                Arguments.of(Transaction.getBalance(), TransactionType.DEPOSIT),
//                Arguments.of(Transaction.getBalance(), TransactionType.TRANSFER),
//                Arguments.of(BigDecimal.ZERO, TransactionType.TRANSFER),
//                Arguments.of(BigDecimal.ZERO, TransactionType.WITHDRAW),
//                Arguments.of(BigDecimal.ONE.negate(), TransactionType.WITHDRAW),
//                Arguments.of(BigDecimal.ZERO, TransactionType.DEPOSIT),
//                Arguments.of(BigDecimal.ONE.negate(), TransactionType.DEPOSIT)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("withdrawParams")
//    public void testWithdraw(final Transaction Transaction, final BigDecimal amount) {
//        if (Transaction.getBalance().compareTo(amount) < 0) {
//            Assertions.assertThrows(InsufficientWithdrawException.class, () -> TransactionValidator.withdraw(Transaction, amount));
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource("depositParams")
//    public void testDeposit(final int id, final BigDecimal amount) {
//        //todo
//    }
//
//    @ParameterizedTest
//    @MethodSource("depositCheckParams")
//    public void testDepositCheck(final int id, final String fullName, final String signature, final BigDecimal amount) {
//
//        if(amount == null) {
//            Assertions.assertThrows(ZeroDepositException.class,
//                    () -> TransactionValidator.depositCheck(id, fullName, signature, amount));
//        } else if(BigDecimal.ZERO.compareTo(amount) >= 0) {
//            Assertions.assertThrows(ZeroDepositException.class,
//                    () -> TransactionValidator.depositCheck(id, fullName, signature, amount));
//        } else if (StringUtils.isBlank(fullName)) {
//            Assertions.assertThrows(NameCheckException.class,
//                    () -> TransactionValidator.depositCheck(id, fullName, signature, amount));
//        } else if (!StringUtils.isAlphaSpace(fullName)) {
//            Assertions.assertThrows(NameCheckException.class,
//                    () -> TransactionValidator.depositCheck(id, fullName, signature, amount));
//        } else if (StringUtils.isBlank(signature)) {
//            Assertions.assertThrows(SignatureCheckException.class,
//                    () -> TransactionValidator.depositCheck(id, fullName, signature, amount));
//        } else if (!StringUtils.isAlphaSpace(signature)) {
//            Assertions.assertThrows(SignatureCheckException.class,
//                    () -> TransactionValidator.depositCheck(id, fullName, signature, amount));
//        } else if(!fullName.equals(signature)) {
//            Assertions.assertThrows(SignatureMismatchException.class,
//                    () -> TransactionValidator.depositCheck(id, fullName, signature, amount));
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource("transferParams")
//    public void testTransfer(Transaction senderTransaction, Transaction receiverTransaction, final BigDecimal amount) {
//        if (senderTransaction.getId().compareTo(receiverTransaction.getId()) == 0) {
//            Assertions.assertThrows(InvalidOperationException.class,
//                                    () -> TransactionValidator.transfer(senderTransaction, receiverTransaction, amount));
//        } else if (senderTransaction.getBalance().compareTo(amount) < 0) {
//            Assertions.assertThrows(InsufficientFundsException.class,
//                                    () -> TransactionValidator.transfer(senderTransaction, receiverTransaction, amount));
//        }
//    }
//
//    @ParameterizedTest
//    @MethodSource("validateAmountParams")
//    void validateTransactionAmount(BigDecimal amount, TransactionType transaction) {
//        final Predicate<BigDecimal> isZero = (a) -> a == null || BigDecimal.ZERO.compareTo(a) >= 0;
//
//        //Check to see if amount is 0, negative or null
//        if (isZero.test(amount)) {
//            if(transaction.equals(TransactionType.WITHDRAW)) {
//                Assertions.assertThrows(ZeroWithdrawException.class,
//                                    () -> TransactionValidator.validateTransactionAmount(amount, transaction));
//            } else if (transaction.equals(TransactionType.DEPOSIT)) {
//                Assertions.assertThrows(ZeroDepositException.class,
//                                    () -> TransactionValidator.validateTransactionAmount(amount, transaction));
//            } else {
//                Assertions.assertThrows(InvalidOperationException.class,
//                                    () -> TransactionValidator.validateTransactionAmount(amount, transaction));
//            }
//        }
//    }
//}