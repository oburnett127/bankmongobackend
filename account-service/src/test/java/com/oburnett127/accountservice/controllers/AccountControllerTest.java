// package com.oburnett127.accountservice.controllers;

// import com.flextrade.jfixture.JFixture;
// import com.oburnett127.accountservice.controller.AccountController;
// import com.oburnett127.accountservice.model.Account;
// import com.oburnett127.accountservice.model.CreateAccountRequest;
// import com.oburnett127.accountservice.model.DepositCheckRequest;
// import com.oburnett127.accountservice.model.DepositRequest;
// import com.oburnett127.accountservice.model.TransferRequest;
// import com.oburnett127.accountservice.model.WithdrawRequest;
// import com.oburnett127.accountservice.service.AccountService;
// import org.junit.jupiter.api.Assertions;
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
// import java.io.IOException;
// import java.math.BigDecimal;
// import java.util.List;
// import java.util.Random;
// import java.util.stream.Stream;

// @ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
// public class AccountControllerTest {

//    @Mock(name = "service")
//    private AccountService service;

//    @InjectMocks
//    private AccountController controller;

//    private static Stream<Arguments> getAccountParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);

//        return Stream.of(
//                Arguments.of(account)
//        );
//    }

//    @Test
//    public void testView() {
//        Mockito.when(service.listAll()).thenReturn(Mockito.mock(List.class));

//        final var actual = controller.view();

//        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
//        Mockito.verify(service).listAll();
//    }

//    @ParameterizedTest
//    @MethodSource("getAccountParams")
//    public void testGetAccount(Account account) {
//        Mockito.when(service.getAccount(account.getId())).thenReturn(account);

//        final var actual = controller.getAccount(account.getId());
//        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
//        Mockito.verify(service).getAccount(account.getId());
//    }

//    @ParameterizedTest
//    @MethodSource("getAccountParams")
//    public void testCreateAccount(Account account) throws IOException {
//        account.setId(0);
//        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
//        createAccountRequest.setFullName(account.getFullName());
//        createAccountRequest.setBalance(account.getBalance());
//        Mockito.doNothing().when(service).createAccount(account);

//        final var actual = controller.createAccount(createAccountRequest);
//        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
//        Mockito.verify(service).createAccount(account);
//    }

//    @ParameterizedTest
//    @MethodSource("getAccountParams")
//    public void testMakeWithdraw(Account account) throws IOException {
//        WithdrawRequest withdrawRequest = new WithdrawRequest();
//        withdrawRequest.setId(account.getId());
//        withdrawRequest.setAmount(account.getBalance());
//        Mockito.when(service.withdraw(account.getId(),account.getBalance())).thenReturn(account);

//        final var actual = controller.makeWithdraw(withdrawRequest);
//        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
//        Mockito.verify(service).withdraw(account.getId(), account.getBalance());
//    }

//    @ParameterizedTest
//    @MethodSource("getAccountParams")
//    public void testMakeDeposit(Account account) {
//        DepositRequest depositRequest = new DepositRequest();
//        depositRequest.setId(account.getId());
//        depositRequest.setAmount(account.getBalance());
//        Mockito.when(service.deposit(account.getId(),account.getBalance())).thenReturn(account);

//        final var actual = controller.makeDeposit(depositRequest);
//        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
//        Mockito.verify(service).deposit(account.getId(), account.getBalance());
//    }

//    @ParameterizedTest
//    @MethodSource("getAccountParams")
//    public void testDepositCheck(Account account) {
//        final var depositCheckRequest = new DepositCheckRequest();
//        final var signature = "Bobby Drillboids";
//        depositCheckRequest.setId(account.getId());
//        depositCheckRequest.setFullName(account.getFullName());
//        depositCheckRequest.setSignature(signature);
//        depositCheckRequest.setAmount(account.getBalance());
//        Mockito.when(service.depositCheck(account.getId(), account.getFullName(),
//                signature, account.getBalance())).thenReturn(account);

//        final var actual = controller.depositCheck(depositCheckRequest);
//        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
//        Mockito.verify(service).depositCheck(account.getId(), account.getFullName(),
//                signature, account.getBalance());
//    }

//    @ParameterizedTest
//    @MethodSource("getAccountParams")
//    public void testTransfer(Account account) {
//        Random random = new Random();
//        final var transferRequest = new TransferRequest();
//        final var receiverId = random.nextInt();
//        final var amount = new BigDecimal("140.00");
//        transferRequest.setIdSender(account.getId());
//        transferRequest.setIdReceiver(receiverId);
//        transferRequest.setAmount(amount);

//        Mockito.when(service.transfer(account.getId(), receiverId, amount)).thenReturn(account);

//        final var actual = controller.transfer(transferRequest);
//        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
//        Mockito.verify(service).transfer(account.getId(), receiverId, amount);
//    }
// }
