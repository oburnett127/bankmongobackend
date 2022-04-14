//package com.oburnett127.transactionservice.daos;
//
//import com.flextrade.jfixture.JFixture;
//import com.oburnett127.transactionservice.models.Transaction;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
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
//import java.util.ArrayList;
//import java.util.UUID;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//public class TransactionDaoTest {
//
//    @InjectMocks
//    private TransactionDao TransactionDao;
//
//    @Mock(name = "sqlSessionFactory")
//    private SqlSessionFactory sqlSessionFactory;
//
//    @BeforeEach
//    public void setup() {}
//
//    public TransactionMapper mockMapperSession() {
//        final var session = Mockito.mock(SqlSession.class);
//        Mockito.when(sqlSessionFactory.openSession()).thenReturn(session);
//
//        final var mapper = Mockito.mock(TransactionMapper.class);
//        Mockito.when(session.getMapper(TransactionMapper.class)).thenReturn(mapper);
//
//        return mapper;
//    }
//
//    private static Stream<Arguments> getTransactionParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        return Stream.of(
//                Arguments.of(Transaction.getId(), Transaction)
//        );
//    }
//
//    private static Stream<Arguments> saveParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        return Stream.of(
//                Arguments.of(Transaction)
//        );
//    }
//
//    private static Stream<Arguments> createParams() {
//        final var jfixture = new JFixture();
//        final var Transaction = jfixture.create(Transaction.class);
//
//        return Stream.of(
//                Arguments.of(Transaction)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("getTransactionParams")
//    public void testGetTransaction(final UUID id, final Transaction expected) {
//        final var mapper = mockMapperSession();
//
//        Mockito.when(mapper.getTransaction(id)).thenReturn(expected);
//
//        final var actual = TransactionDao.getTransaction(id);
//        Assertions.assertEquals(expected, actual);
//
//        Mockito.verify(mapper).getTransaction(id);
//    }
//
//    @Test
//    public void testGetAll() {
//        final var expected = new ArrayList<Transaction>();
//        IntStream.of(1, 2, 3).forEach(i -> {
//            final var Transaction = Transaction.builder()
//                    .id(UUID.randomUUID())
//                    .fullName(Integer.toString(i))
//                    .balance(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(5000L)))
//                    .build();
//
//            expected.add(Transaction);
//        });
//
//        final var mapper = mockMapperSession();
//        Mockito.when(mapper.getAll()).thenReturn(expected);
//
//        final var actual = TransactionDao.getAll();
//        Assertions.assertEquals(expected, actual);
//
//        Mockito.verify(mapper).getAll();
//    }
//
//    @ParameterizedTest
//    @MethodSource("saveParams")
//    public void testSave(final Transaction Transaction) {
//        final var session = Mockito.mock(SqlSession.class);
//        Mockito.when(sqlSessionFactory.openSession()).thenReturn(session);
//
//        final var mapper = Mockito.mock(TransactionMapper.class);
//        Mockito.when(session.getMapper(TransactionMapper.class)).thenReturn(mapper);
//
//        TransactionDao.save(Transaction);
//
//        Mockito.verify(mapper).save(Transaction);
//        Mockito.verify(session).commit();
//        Mockito.verify(session, Mockito.never()).rollback();
//    }
//
//    @ParameterizedTest
//    @MethodSource("createParams")
//    public void testCreate(final Transaction Transaction) {
//        final var session = Mockito.mock(SqlSession.class);
//        Mockito.when(sqlSessionFactory.openSession()).thenReturn(session);
//
//        final var mapper = Mockito.mock(TransactionMapper.class);
//        Mockito.when(session.getMapper(TransactionMapper.class)).thenReturn(mapper);
//
//        TransactionDao.create(Transaction);
//
//        Mockito.verify(mapper).create(Transaction);
//        Mockito.verify(session).commit();
//        Mockito.verify(session, Mockito.never()).rollback();
//    }
//}
