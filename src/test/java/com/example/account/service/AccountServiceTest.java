package com.example.account.service;

import com.example.account.TestSupport;
import com.example.account.dto.*;
import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.Transaction;
import com.example.account.model.TransactionType;
import com.example.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AccountServiceTest extends TestSupport {

    private AccountRepository accountRepository;
    private CustomerService customerService;
    private AccountDtoConverter converter;

    private AccountService accountService;

    private final Customer customer = generateCustomer();
    private final AccountCustomerDto customerDto = new AccountCustomerDto(
            "customer-id",
            "customer-name",
            "customer-surname");

    @BeforeEach
    public void setUp() {
        accountRepository = mock(AccountRepository.class);
        customerService = mock(CustomerService.class);
        converter = mock(AccountDtoConverter.class);
        Clock clock = mock(Clock.class);
        accountService = new AccountService(accountRepository, customerService, converter, clock);
        when(clock.instant()).thenReturn(getCurrentInstant());
        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    public void testCreateAccount_whenCustomerIdExistsAndInitialCreditGreaterThanZero_shouldCreateAccountWithTransaction() {

        CreateAccountRequest request = generateCreateAccountRequest(100);
        Account account = generateAccount(100);
        Transaction transaction = new Transaction(null, TransactionType.INITIAL, new BigDecimal(100), getLocalDateTime(), account);
        account.getTransactions().add(transaction);

        TransactionDto transactionDto = new TransactionDto("", TransactionType.INITIAL, new BigDecimal(100), getLocalDateTime());
        AccountDto expected = new AccountDto("account-id", new BigDecimal(100), getLocalDateTime(), customerDto, List.of(transactionDto));

        when(customerService.findCustomerById("customer-id")).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);

        when(converter.convert(account)).thenReturn(expected);

        AccountDto result = accountService.createAccount(request);

        assertEquals(expected, result);

    }

    private Account generateAccount(int balance) {
        return new Account(null, new BigDecimal(balance), getLocalDateTime(), customer, new ArrayList<>());
    }




}
