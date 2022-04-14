//package com.oburnett127.account-service.daos;
//
//import com.flextrade.jfixture.JFixture;
//import com.oburnett127.account-service.models.Account;
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
//public class AccountDaoTest {
//
//    @InjectMocks
//    private AccountDao accountDao;
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
//    private static Stream<Arguments> getAccountParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);
//
//        return Stream.of(
//                Arguments.of(account.getId(), account)
//        );
//    }
//
//    private static Stream<Arguments> saveParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);
//
//        return Stream.of(
//                Arguments.of(account)
//        );
//    }
//
//    private static Stream<Arguments> createParams() {
//        final var jfixture = new JFixture();
//        final var account = jfixture.create(Account.class);
//
//        return Stream.of(
//                Arguments.of(account)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("getAccountParams")
//    public void testGetAccount(final UUID id, final Account expected) {
//        final var mapper = mockMapperSession();
//
//        Mockito.when(mapper.getTransaction(id)).thenReturn(expected);
//
//        final var actual = accountDao.getAccount(id);
//        Assertions.assertEquals(expected, actual);
//
//        Mockito.verify(mapper).getTransaction(id);
//    }
//
//    @Test
//    public void testGetAll() {
//        final var expected = new ArrayList<Account>();
//        IntStream.of(1, 2, 3).forEach(i -> {
//            final var account = Account.builder()
//                    .id(UUID.randomUUID())
//                    .fullName(Integer.toString(i))
//                    .balance(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(5000L)))
//                    .build();
//
//            expected.add(account);
//        });
//
//        final var mapper = mockMapperSession();
//        Mockito.when(mapper.getAll()).thenReturn(expected);
//
//        final var actual = accountDao.getAll();
//        Assertions.assertEquals(expected, actual);
//
//        Mockito.verify(mapper).getAll();
//    }
//
//    @ParameterizedTest
//    @MethodSource("saveParams")
//    public void testSave(final Account account) {
//        final var session = Mockito.mock(SqlSession.class);
//        Mockito.when(sqlSessionFactory.openSession()).thenReturn(session);
//
//        final var mapper = Mockito.mock(TransactionMapper.class);
//        Mockito.when(session.getMapper(TransactionMapper.class)).thenReturn(mapper);
//
//        accountDao.save(account);
//
//        Mockito.verify(mapper).save(account);
//        Mockito.verify(session).commit();
//        Mockito.verify(session, Mockito.never()).rollback();
//    }
//
//    @ParameterizedTest
//    @MethodSource("createParams")
//    public void testCreate(final Account account) {
//        final var session = Mockito.mock(SqlSession.class);
//        Mockito.when(sqlSessionFactory.openSession()).thenReturn(session);
//
//        final var mapper = Mockito.mock(TransactionMapper.class);
//        Mockito.when(session.getMapper(TransactionMapper.class)).thenReturn(mapper);
//
//        accountDao.create(account);
//
//        Mockito.verify(mapper).create(account);
//        Mockito.verify(session).commit();
//        Mockito.verify(session, Mockito.never()).rollback();
//    }
//}
