// package com.oburnett127.accountservice.utils;

// import com.flextrade.jfixture.JFixture;
// import com.oburnett127.accountservice.constant.TransactionType;
// import com.oburnett127.accountservice.exception.InsufficientFundsException;
// import com.oburnett127.accountservice.exception.InsufficientWithdrawException;
// import com.oburnett127.accountservice.exception.InvalidOperationException;
// import com.oburnett127.accountservice.exception.NameCheckException;
// import com.oburnett127.accountservice.exception.SignatureCheckException;
// import com.oburnett127.accountservice.exception.SignatureMismatchException;
// import com.oburnett127.accountservice.exception.ZeroDepositException;
// import com.oburnett127.accountservice.exception.ZeroWithdrawException;
// import com.oburnett127.accountservice.model.Account;
// import com.oburnett127.accountservice.util.AccountValidator;
// import org.apache.commons.lang3.StringUtils;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;
// import org.mockito.InjectMocks;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.mockito.junit.jupiter.MockitoSettings;
// import org.mockito.quality.Strictness;
// import java.math.BigDecimal;
// import java.util.function.Predicate;
// import java.util.stream.Stream;
// import java.util.Random;

// @ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
// public class AccountValidatorTest {

//    @InjectMocks
//    private AccountValidator accountValidator;

//    private static Stream<Arguments> withdrawParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);

//        // id | amount | account
//        return Stream.of(
//            Arguments.of(account, account.getBalance()),
//            Arguments.of(account, account.getBalance().add(BigDecimal.ONE)),
//            Arguments.of(account, account.getBalance().negate()),
//            Arguments.of(account, BigDecimal.ZERO)
//        );
//    }

//    private static Stream<Arguments> depositParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);

//        // id | amount | account
//        return Stream.of(
//                Arguments.of(account.getId(), account.getBalance()),
//                Arguments.of(null, account.getBalance()),
//                Arguments.of(account.getId(), account.getBalance().negate()),
//                Arguments.of(null, account.getBalance().negate()),
//                Arguments.of(account.getId(), BigDecimal.ZERO),
//                Arguments.of(null, BigDecimal.ZERO)
//        );
//    }

//    private static Stream<Arguments> depositCheckParams() {
//         Random random = new Random();
//         final var int1 = random.nextInt();
//         final var fullName = "Adam Smith";

//        // id | amount | account
//        return Stream.of(
//                Arguments.of(int1, fullName, fullName, new BigDecimal("100.00")),
//                Arguments.of(null, fullName, fullName, new BigDecimal("100.00")),
//                Arguments.of(int1, fullName, fullName, null),
//                Arguments.of(int1, null, fullName, new BigDecimal("100.00")),
//                Arguments.of(int1, fullName, null, new BigDecimal("100.00")),
//                Arguments.of(int1, fullName, fullName, BigDecimal.ONE),
//                Arguments.of(null, fullName, fullName, BigDecimal.ONE),
//                Arguments.of(int1, null, fullName, BigDecimal.ONE),
//                Arguments.of(int1, fullName, null, BigDecimal.ONE),
//                Arguments.of(int1, fullName, fullName, BigDecimal.ONE.negate()),
//                Arguments.of(null, fullName, fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int1, null, fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int1, fullName, null, BigDecimal.ONE.negate()),
//                Arguments.of(int1, fullName, fullName, BigDecimal.ZERO),
//                Arguments.of(null, fullName, fullName, BigDecimal.ZERO),
//                Arguments.of(int1, null, fullName, BigDecimal.ZERO),
//                Arguments.of(int1, fullName, null, BigDecimal.ZERO),
//                Arguments.of(int1, "", fullName, new BigDecimal("100.00")),
//                Arguments.of(null, "", fullName, new BigDecimal("100.00")),
//                Arguments.of(int1, "", null, new BigDecimal("100.00")),
//                Arguments.of(int1, "", fullName, BigDecimal.ONE),
//                Arguments.of(int1, "", fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int1, "", fullName, BigDecimal.ZERO),
//                Arguments.of(null, "", fullName, BigDecimal.ONE),
//                Arguments.of(int1, "", null, BigDecimal.ONE),
//                Arguments.of(null, "", fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int1, "", null, BigDecimal.ONE),
//                Arguments.of(null, fullName + "123", fullName, new BigDecimal("100.00")),
//                Arguments.of(int1, fullName + "123", null, new BigDecimal("100.00")),
//                Arguments.of(int1, fullName + "123", fullName, BigDecimal.ONE),
//                Arguments.of(null, fullName + "123", fullName, BigDecimal.ONE),
//                Arguments.of(int1, fullName + "123", null, BigDecimal.ONE),
//                Arguments.of(int1, fullName + "123", fullName, BigDecimal.ONE.negate()),
//                Arguments.of(null, fullName + "123", fullName, BigDecimal.ONE.negate()),
//                Arguments.of(int1, fullName + "123", null, BigDecimal.ONE.negate()),
//                Arguments.of(int1, fullName + "123", fullName, BigDecimal.ZERO),
//                Arguments.of(null, fullName + "123", fullName, BigDecimal.ZERO),
//                Arguments.of(int1, fullName + "123", null, BigDecimal.ZERO),
//                Arguments.of(int1, fullName + "123", null, BigDecimal.ONE),
//                Arguments.of(int1, fullName + "123", null, BigDecimal.ONE.negate()),
//                Arguments.of(int1, fullName, fullName + "$123", new BigDecimal("100.00")),
//                Arguments.of(null, fullName, fullName + "$123", new BigDecimal("100.00")),
//                Arguments.of(int1, null, fullName + "$123", new BigDecimal("100.00")),
//                Arguments.of(int1, fullName, fullName + "$123", BigDecimal.ZERO),
//                Arguments.of(int1, fullName, fullName + "$123", BigDecimal.ONE),
//                Arguments.of(int1, fullName, fullName + "$123", BigDecimal.ONE.negate()),
//                Arguments.of(int1, fullName + "abcdefg", fullName, new BigDecimal("100.00")),
//                Arguments.of(null, fullName + "abcdefg", fullName, new BigDecimal("100.00")),
//                Arguments.of(int1, fullName + "abcdefg", null, new BigDecimal("100.00")),
//                Arguments.of(int1, fullName + "abcdefg", fullName, BigDecimal.ZERO),
//                Arguments.of(int1, fullName + "abcdefg", fullName, BigDecimal.ONE),
//                Arguments.of(int1, fullName + "abcdefg", fullName, BigDecimal.ONE.negate())
//        );
//    }

//    private static Stream<Arguments> transferParams() {
//        final var jfixture = new JFixture();
//        final var sender = jfixture.create(Account.class);
//        final var receiver = jfixture.create(Account.class);

//        // id | amount | account
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

//    private static Stream<Arguments> validateAmountParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);

//        return Stream.of(
//                Arguments.of(account.getBalance(), TransactionType.WITHDRAW),
//                Arguments.of(account.getBalance(), TransactionType.DEPOSIT),
//                Arguments.of(account.getBalance(), TransactionType.TRANSFER),
//                Arguments.of(BigDecimal.ZERO, TransactionType.TRANSFER),
//                Arguments.of(BigDecimal.ZERO, TransactionType.WITHDRAW),
//                Arguments.of(BigDecimal.ONE.negate(), TransactionType.WITHDRAW),
//                Arguments.of(BigDecimal.ZERO, TransactionType.DEPOSIT),
//                Arguments.of(BigDecimal.ONE.negate(), TransactionType.DEPOSIT)
//        );
//    }

//    @ParameterizedTest
//    @MethodSource("withdrawParams")
//    public void testWithdraw(final Account account, final BigDecimal amount) {
//        if (account.getBalance().compareTo(amount) < 0) {
//            Assertions.assertThrows(InsufficientWithdrawException.class, () -> accountValidator.withdraw(account, amount));
//        }
//    }

//    @ParameterizedTest
//    @MethodSource("depositParams")
//    public void testDeposit(final int id, final BigDecimal amount) {
//        //todo
//    }

//    @ParameterizedTest
//    @MethodSource("depositCheckParams")
//    public void testDepositCheck(final int id, final String fullName, final String signature, final BigDecimal amount) {

//        if(amount == null) {
//            Assertions.assertThrows(ZeroDepositException.class,
//                    () -> accountValidator.depositCheck(id, fullName, signature, amount));
//        } else if(BigDecimal.ZERO.compareTo(amount) >= 0) {
//            Assertions.assertThrows(ZeroDepositException.class,
//                    () -> accountValidator.depositCheck(id, fullName, signature, amount));
//        } else if (StringUtils.isBlank(fullName)) {
//            Assertions.assertThrows(NameCheckException.class,
//                    () -> accountValidator.depositCheck(id, fullName, signature, amount));
//        } else if (!StringUtils.isAlphaSpace(fullName)) {
//            Assertions.assertThrows(NameCheckException.class,
//                    () -> accountValidator.depositCheck(id, fullName, signature, amount));
//        } else if (StringUtils.isBlank(signature)) {
//            Assertions.assertThrows(SignatureCheckException.class,
//                    () -> accountValidator.depositCheck(id, fullName, signature, amount));
//        } else if (!StringUtils.isAlphaSpace(signature)) {
//            Assertions.assertThrows(SignatureCheckException.class,
//                    () -> accountValidator.depositCheck(id, fullName, signature, amount));
//        } else if(!fullName.equals(signature)) {
//            Assertions.assertThrows(SignatureMismatchException.class,
//                    () -> accountValidator.depositCheck(id, fullName, signature, amount));
//        }
//    }

//    @ParameterizedTest
//    @MethodSource("transferParams")
//    public void testTransfer(Account senderAccount, Account receiverAccount, final BigDecimal amount) {
//        if (((Integer)senderAccount.getId()).compareTo((Integer)receiverAccount.getId()) == 0) {
//            Assertions.assertThrows(InvalidOperationException.class,
//                                    () -> accountValidator.transfer(senderAccount, receiverAccount, amount));
//        } else if (senderAccount.getBalance().compareTo(amount) < 0) {
//            Assertions.assertThrows(InsufficientFundsException.class,
//                                    () -> accountValidator.transfer(senderAccount, receiverAccount, amount));
//        }
//    }

//    @ParameterizedTest
//    @MethodSource("validateAmountParams")
//    void validateTransactionAmount(BigDecimal amount, TransactionType transaction) {
//        final Predicate<BigDecimal> isZero = (a) -> a == null || BigDecimal.ZERO.compareTo(a) >= 0;

//        //Check to see if amount is 0, negative or null
//        if (isZero.test(amount)) {
//            if(transaction.equals(TransactionType.WITHDRAW)) {
//                Assertions.assertThrows(ZeroWithdrawException.class,
//                                    () -> accountValidator.validateTransactionAmount(amount, transaction));
//            } else if (transaction.equals(TransactionType.DEPOSIT)) {
//                Assertions.assertThrows(ZeroDepositException.class,
//                                    () -> accountValidator.validateTransactionAmount(amount, transaction));
//            } else {
//                Assertions.assertThrows(InvalidOperationException.class,
//                                    () -> accountValidator.validateTransactionAmount(amount, transaction));
//            }
//        }
//    }
// }