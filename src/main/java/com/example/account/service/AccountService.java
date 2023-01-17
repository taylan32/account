package com.example.account.service;

import com.example.account.dto.AccountDto;
import com.example.account.dto.AccountDtoConverter;
import com.example.account.dto.CreateAccountRequest;
import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.Transaction;
import com.example.account.model.TransactionType;
import com.example.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;



@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;
    private final Clock clock;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter,
                          Clock clock) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
        this.clock = clock;
    }

    public AccountDto createAccount(CreateAccountRequest request) {

        Customer customer = customerService.findCustomerById(request.getCustomerId());
        Account account = new Account(
                null,
                request.getInitialCredit(),
                getLocalDateTimeNow(),
                customer,
                new ArrayList<>());

        if(request.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(
                    null,
                    TransactionType.INITIAL,
                    request.getInitialCredit(),
                    getLocalDateTimeNow(),
                    account);

            account.getTransactions().add(transaction);

        }
        return converter.convert(accountRepository.save(account));
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant, Clock.systemDefaultZone().getZone()
        );
    }


}
