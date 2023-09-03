// package com.oburnett127.accountservice.services;

// import com.flextrade.jfixture.JFixture;
// import com.oburnett127.accountservice.repository.AccountRepository;
// import com.oburnett127.accountservice.service.AccountService;
// import com.oburnett127.accountservice.model.Account;
// import com.oburnett127.accountservice.util.AccountValidator;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.mockito.junit.jupiter.MockitoSettings;
// import org.mockito.quality.Strictness;
// import java.math.BigDecimal;
// import java.util.Optional;
// import java.util.Random;
// import java.util.function.Predicate;
// import java.util.stream.Stream;

// @ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
// public class AccountServiceTest {

//    @InjectMocks
//    private AccountService accountService;

//    @Mock(name = "accountRepository")
//    private AccountRepository accountRepository;

//    @Mock(name = "accountValidator")
//    private AccountValidator accountValidator;

//     private static Stream<Arguments> withdrawParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);

//        // id | amount | account
//        return Stream.of(
//            Arguments.of(account.getId(), account.getBalance(), account),
//            Arguments.of(null, account.getBalance(), account),
//            Arguments.of(account.getId(), BigDecimal.ZERO, account),
//            Arguments.of(account.getId(), account.getBalance().negate(), account),
//            Arguments.of(account.getId(), account.getBalance().add(BigDecimal.ONE), account)
//        );
//    }

//     private static Stream<Arguments> getAccountParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);

//        return Stream.of(
//                Arguments.of(account)
//        );
//    }

//     private static Stream<Arguments> getAccountIdParam() {
//         Random random = new Random();
//         final var int1 = random.nextInt();

//         return Stream.of(
//             Arguments.of(int1)
//         );
//     }

//     private static Stream<Arguments> depositParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);

//        // id | amount | account
//        return Stream.of(
//                Arguments.of(account.getId(), account.getBalance(), account),
//                Arguments.of(null, account.getBalance(), account),
//                Arguments.of(account.getId(), BigDecimal.ZERO, account),
//                Arguments.of(null, BigDecimal.ZERO, account),
//                Arguments.of(account.getId(), account.getBalance().negate(), account),
//                Arguments.of(null, account.getBalance().negate(), account),
//                Arguments.of(account.getId(), account.getBalance().add(BigDecimal.ONE), account),
//                Arguments.of(null, account.getBalance().add(BigDecimal.ONE), account)
//        );
//     }

//    private static Stream<Arguments> depositCheckParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);
//        final var fullName = account.getFullName();

//        // id | amount | account
//        return Stream.of(
//                Arguments.of(account.getId(), account.getBalance(), account, fullName, fullName),
//                Arguments.of(null, account.getBalance(), account, fullName, fullName),
//                Arguments.of(account.getId(), account.getBalance(), account, null, fullName),
//                Arguments.of(account.getId(), account.getBalance(), account, fullName, null),
//                Arguments.of(account.getId(), BigDecimal.ZERO, account, fullName, fullName),
//                Arguments.of(account.getId(), account.getBalance().negate(), account, fullName, fullName),
//                Arguments.of(account.getId(), account.getBalance().add(BigDecimal.ONE), account, fullName, fullName)
//        );
//    }

//    private static Stream<Arguments> transferParams() {
//        final var jfixture = new JFixture();
//        final var sender = jfixture.create(Account.class);
//        final var receiver = jfixture.create(Account.class);

//        // id | amount | account
//        return Stream.of(
//                Arguments.of(sender.getId(), receiver.getId(), sender.getBalance(), sender, receiver),
//                Arguments.of(null, receiver.getId(), sender.getBalance(), sender, receiver),
//                Arguments.of(sender.getId(), null, sender.getBalance(), sender, receiver),
//                Arguments.of(sender.getId(), receiver.getId(), sender.getBalance().add(BigDecimal.ONE), sender, receiver),
//                Arguments.of(sender.getId(), receiver.getId(), sender.getBalance().negate(), sender, receiver),
//                Arguments.of(sender.getId(), receiver.getId(), BigDecimal.ZERO, sender, receiver)
//        );
//    }

//    @Test
//    public void testListAll() {
//        final var actual = accountService.listAll();
//        Mockito.verify(accountRepository).findAll();
//    }

//    @ParameterizedTest
//    @MethodSource("getAccountParams")
//    public void testCreateAccount(Account account) {
//        accountService.createAccount(account);
//        Mockito.verify(accountRepository).save(account);
//    }

//    @ParameterizedTest
//    @MethodSource("getAccountIdParam")
//    public void testGetAccount(final int id) {
//        Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(Mockito.mock(Account.class)));
//        accountService.getAccount(id);
//        Mockito.verify(accountRepository).findById(id);
//    }

//    @ParameterizedTest
//    @MethodSource("withdrawParams")
//    public void testWithdraw(final int id, final BigDecimal amount, final Account account) {
//        Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(account));

//        final var actual = accountService.withdraw(id, amount);

//        Mockito.verify(accountValidator, Mockito.times(1)).withdraw(account, amount);

//        Mockito.verify(accountRepository).save(actual);
//    }

//    @ParameterizedTest
//    @MethodSource("depositParams")
//    public void testDeposit(final int id, final BigDecimal amount, Account account) {
//        Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(account));

//        final var actual = accountService.deposit(id, amount);

//        Mockito.verify(accountValidator, Mockito.times(1)).deposit(id, amount);

//        Mockito.verify(accountRepository).save(actual);
//    }

//    @ParameterizedTest
//    @MethodSource("depositCheckParams")
//    public void testDepositCheck(final int id,  final BigDecimal amount, Account account,
//                                 final String fullName, final String signature) {
//        Mockito.when(accountRepository.findById(id)).thenReturn(Optional.of(account));

//        final var actual = accountService.depositCheck(id, fullName, signature, amount);

//        Mockito.verify(accountValidator, Mockito.times(1)).depositCheck(id, fullName,
//                signature, amount);

//        Mockito.verify(accountRepository, Mockito.times(1)).save(actual);
//    }

//    @ParameterizedTest
//    @MethodSource("transferParams")
//    public void testTransfer(final int idSender, final int idReceiver, final BigDecimal amount, Account senderAccount,
//                             Account receiverAccount) {
//        final Predicate<BigDecimal> isZero = (a) -> a == null || BigDecimal.ZERO.compareTo(a) >= 0;

//        Mockito.when(accountRepository.findById(idSender)).thenReturn(Optional.of(senderAccount));
//        Mockito.when(accountRepository.findById(idReceiver)).thenReturn(Optional.of(receiverAccount));

//        final var actualSender = accountService.transfer(idSender, idReceiver, amount);

//        Mockito.verify(accountValidator, Mockito.times(1)).transfer(senderAccount, receiverAccount, amount);

//        Mockito.verify(accountRepository).save(actualSender);
//    }
// }
