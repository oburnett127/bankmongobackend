package com.oburnett127.transactionservice.controllers;

import com.flextrade.jfixture.JFixture;
import com.oburnett127.transactionservice.models.*;
import com.oburnett127.transactionservice.services.transactionservice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionControllerTest {

    @Mock(name = "service")
    private TransactionService service;

    @InjectMocks
    private TransactionController controller;

    private static Stream<Arguments> getTransactionParams() {
        final var jfixture = new JFixture();
        final var Transaction = jfixture.create(Transaction.class);

        return Stream.of(
                Arguments.of(Transaction)
        );
    }

    @Test
    public void testView() {
        Mockito.when(service.listAll()).thenReturn(Mockito.mock(List.class));

        final var actual = controller.view();

        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Mockito.verify(service).listAll();
    }

    @ParameterizedTest
    @MethodSource("getTransactionParams")
    public void testGetTransaction(Transaction Transaction) {
        TransactionRequest TransactionRequest = new TransactionRequest();
        TransactionRequest.setId(Transaction.getId());
        Mockito.when(service.getTransaction(Transaction.getId())).thenReturn(Transaction);

        final var actual = controller.getTransaction(TransactionRequest);
        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Mockito.verify(service).getTransaction(Transaction.getId());
    }

    @ParameterizedTest
    @MethodSource("getTransactionParams")
    public void testCreateTransaction(Transaction Transaction) throws IOException {
        Transaction.setId(null);
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setFullName(Transaction.getFullName());
        createTransactionRequest.setBalance(Transaction.getBalance());
        Mockito.doNothing().when(service).createTransaction(Transaction);

        final var actual = controller.createTransaction(createTransactionRequest);
        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Mockito.verify(service).createTransaction(Transaction);
    }

    @ParameterizedTest
    @MethodSource("getTransactionParams")
    public void testMakeWithdraw(Transaction Transaction) throws IOException {
        UpdateTransactionRequest withdrawRequest = new UpdateTransactionRequest();
        withdrawRequest.setId(Transaction.getId());
        withdrawRequest.setAmount(Transaction.getBalance());
        Mockito.when(service.withdraw(Transaction.getId(),Transaction.getBalance())).thenReturn(Transaction);

        final var actual = controller.makeWithdraw(withdrawRequest);
        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Mockito.verify(service).withdraw(Transaction.getId(), Transaction.getBalance());
    }

    @ParameterizedTest
    @MethodSource("getTransactionParams")
    public void testMakeDeposit(Transaction Transaction) {
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setId(Transaction.getId());
        depositRequest.setAmount(Transaction.getBalance());
        Mockito.when(service.deposit(Transaction.getId(),Transaction.getBalance())).thenReturn(Transaction);

        final var actual = controller.makeDeposit(depositRequest);
        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Mockito.verify(service).deposit(Transaction.getId(), Transaction.getBalance());
    }

    @ParameterizedTest
    @MethodSource("getTransactionParams")
    public void testDepositCheck(Transaction Transaction) {
        final var depositCheckRequest = new DepositCheckRequest();
        final var signature = "Bobby Drillboids";
        depositCheckRequest.setId(Transaction.getId());
        depositCheckRequest.setFullName(Transaction.getFullName());
        depositCheckRequest.setSignature(signature);
        depositCheckRequest.setAmount(Transaction.getBalance());
        Mockito.when(service.depositCheck(Transaction.getId(), Transaction.getFullName(),
                signature, Transaction.getBalance())).thenReturn(Transaction);

        final var actual = controller.depositCheck(depositCheckRequest);
        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Mockito.verify(service).depositCheck(Transaction.getId(), Transaction.getFullName(),
                signature, Transaction.getBalance());
    }

    @ParameterizedTest
    @MethodSource("getTransactionParams")
    public void testTransfer(Transaction Transaction) {
        final var transferRequest = new TransferRequest();
        final var receiverId = UUID.randomUUID();
        final var amount = new BigDecimal("140.00");
        transferRequest.setIdSender(Transaction.getId());
        transferRequest.setIdReceiver(receiverId);
        transferRequest.setAmount(amount);

        Mockito.when(service.transfer(Transaction.getId(), receiverId, amount)).thenReturn(Transaction);

        final var actual = controller.transfer(transferRequest);
        Assertions.assertTrue(actual.getStatusCode().is2xxSuccessful());
        Mockito.verify(service).transfer(Transaction.getId(), receiverId, amount);
    }
}
