//package com.oburnett127.transactionservice.services;
//
//
//import com.flextrade.jfixture.JFixture;
//import com.oburnett127.transactionservice.daos.TransactionRepository;
//import com.oburnett127.transactionservice.utils.TransactionValidator;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
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
//public class TransactionServiceTest {
//
//    @InjectMocks
//    private TransactionService transactionService;
//
//    @Mock(name = "TransactionRepository")
//    private TransactionRepository TransactionRepository;
//
//    @Mock(name = "TransactionValidator")
//    private TransactionValidator TransactionValidator;
//
//    private static Stream<Arguments> withdrawParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        // id | amount | Transaction
//        return Stream.of(
//            Arguments.of(Transaction.getId(), Transaction.getBalance(), Transaction),
//            Arguments.of(null, Transaction.getBalance(), Transaction),
//            Arguments.of(Transaction.getId(), BigDecimal.ZERO, Transaction),
//            Arguments.of(Transaction.getId(), Transaction.getBalance().negate(), Transaction),
//            Arguments.of(Transaction.getId(), Transaction.getBalance().add(BigDecimal.ONE), Transaction)
//        );
//    }
//    private static Stream<Arguments> getTransactionParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        return Stream.of(
//                Arguments.of(Transaction)
//        );
//    }
//
//    private static Stream<Arguments> getTransactionIdParam() {
//        final var int = int.randomint();
//
//        return Stream.of(
//                Arguments.of(int)
//        );
//    }
//
//    private static Stream<Arguments> depositParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        // id | amount | Transaction
//        return Stream.of(
//                Arguments.of(Transaction.getId(), Transaction.getBalance(), Transaction),
//                Arguments.of(null, Transaction.getBalance(), Transaction),
//                Arguments.of(Transaction.getId(), BigDecimal.ZERO, Transaction),
//                Arguments.of(null, BigDecimal.ZERO, Transaction),
//                Arguments.of(Transaction.getId(), Transaction.getBalance().negate(), Transaction),
//                Arguments.of(null, Transaction.getBalance().negate(), Transaction),
//                Arguments.of(Transaction.getId(), Transaction.getBalance().add(BigDecimal.ONE), Transaction),
//                Arguments.of(null, Transaction.getBalance().add(BigDecimal.ONE), Transaction)
//        );
//    }
//
//    private static Stream<Arguments> depositCheckParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//        final var fullName = Transaction.getFullName();
//
//        // id | amount | Transaction
//        return Stream.of(
//                Arguments.of(Transaction.getId(), Transaction.getBalance(), Transaction, fullName, fullName),
//                Arguments.of(null, Transaction.getBalance(), Transaction, fullName, fullName),
//                Arguments.of(Transaction.getId(), Transaction.getBalance(), Transaction, null, fullName),
//                Arguments.of(Transaction.getId(), Transaction.getBalance(), Transaction, fullName, null),
//                Arguments.of(Transaction.getId(), BigDecimal.ZERO, Transaction, fullName, fullName),
//                Arguments.of(Transaction.getId(), Transaction.getBalance().negate(), Transaction, fullName, fullName),
//                Arguments.of(Transaction.getId(), Transaction.getBalance().add(BigDecimal.ONE), Transaction, fullName, fullName)
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
//                Arguments.of(sender.getId(), receiver.getId(), sender.getBalance(), sender, receiver),
//                Arguments.of(null, receiver.getId(), sender.getBalance(), sender, receiver),
//                Arguments.of(sender.getId(), null, sender.getBalance(), sender, receiver),
//                Arguments.of(sender.getId(), receiver.getId(), sender.getBalance().add(BigDecimal.ONE), sender, receiver),
//                Arguments.of(sender.getId(), receiver.getId(), sender.getBalance().negate(), sender, receiver),
//                Arguments.of(sender.getId(), receiver.getId(), BigDecimal.ZERO, sender, receiver)
//        );
//    }
//
//    @Test
//    public void testListAll() {
//        final var actual = transactionService.listAll();
//        Mockito.verify(TransactionRepository).getAll();
//    }
//
//    @ParameterizedTest
//    @MethodSource("getTransactionParams")
//    public void testCreateTransaction(Transaction Transaction) {
//        transactionService.createTransaction(Transaction);
//        Mockito.verify(TransactionRepository).create(Transaction);
//    }
//
//    @ParameterizedTest
//    @MethodSource("getTransactionIdParam")
//    public void testGetTransaction(final int id) {
//        Mockito.when(TransactionRepository.getTransaction(id)).thenReturn(Mockito.mock(Transaction.class));
//        transactionService.getTransaction(id);
//        Mockito.verify(TransactionRepository).getTransaction(id);
//    }
//
//    @ParameterizedTest
//    @MethodSource("withdrawParams")
//    public void testWithdraw(final int id, final BigDecimal amount, final Transaction Transaction) {
//        Mockito.when(TransactionRepository.getTransaction(id)).thenReturn(Transaction);
//
//        final var actual = transactionservice.withdraw(id, amount);
//
//        Mockito.verify(TransactionValidator, Mockito.times(1)).withdraw(Transaction, amount);
//
//        Mockito.verify(TransactionRepository).save(actual);
//    }
//
//    @ParameterizedTest
//    @MethodSource("depositParams")
//    public void testDeposit(final int id, final BigDecimal amount, Transaction Transaction) {
//        Mockito.when(TransactionRepository.getTransaction(id)).thenReturn(Transaction);
//
//        final var actual = transactionservice.deposit(id, amount);
//
//        Mockito.verify(TransactionValidator, Mockito.times(1)).deposit(id, amount);
//
//        Mockito.verify(TransactionRepository).save(actual);
//    }
//
//    @ParameterizedTest
//    @MethodSource("depositCheckParams")
//    public void testDepositCheck(final int id,  final BigDecimal amount, Transaction Transaction,
//                                 final String fullName, final String signature) {
//        Mockito.when(TransactionRepository.getTransaction(id)).thenReturn(Transaction);
//
//        final var actual = transactionservice.depositCheck(id, fullName, signature, amount);
//
//        Mockito.verify(TransactionValidator, Mockito.times(1)).depositCheck(id, fullName,
//                signature, amount);
//
//        Mockito.verify(TransactionRepository, Mockito.times(1)).save(actual);
//    }
//
//    @ParameterizedTest
//    @MethodSource("transferParams")
//    public void testTransfer(final int idSender, final int idReceiver, final BigDecimal amount, Transaction senderTransaction,
//                             Transaction receiverTransaction) {
//        final Predicate<BigDecimal> isZero = (a) -> a == null || BigDecimal.ZERO.compareTo(a) >= 0;
//
//        Mockito.when(TransactionRepository.getTransaction(idSender)).thenReturn(senderTransaction);
//        Mockito.when(TransactionRepository.getTransaction(idReceiver)).thenReturn(receiverTransaction);
//
//        final var actualSender = transactionservice.transfer(idSender, idReceiver, amount);
//
//        Mockito.verify(TransactionValidator, Mockito.times(1)).transfer(senderTransaction, receiverTransaction, amount);
//
//        Mockito.verify(TransactionRepository).save(actualSender);
//    }
//}
