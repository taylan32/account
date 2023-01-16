package com.example.account.service;

import com.example.account.dto.AccountDto;
import com.example.account.dto.AccountDtoConverter;
import com.example.account.dto.CreateAccountRequest;
import com.example.account.model.Account;
import com.example.account.model.Customer;
import com.example.account.model.Transaction;
import com.example.account.model.TransactionType;
import com.example.account.repository.AccountRepository;
import org.springframework.stereotype.Service;


import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest request) {

        Customer customer = customerService.findCustomerById(request.getCustomerId());
        Account account = new Account(
                null,
                request.getInitialCredit(),
                new Date(),
                customer,
                new ArrayList<>());

        if(request.getInitialCredit().compareTo(Long.valueOf(0)) > 0) {
           /* Transaction transaction = transactionService.initiateMoney(account, request.getInitialCredit());
            account.getTransactions().add(transaction);*/
            // OneToMany de cascase all. account a transaction eklediğimiz zaman db ye eklenir.
            // TODO: transaction service sil.
            // costructor dan silindi.
            Transaction transaction = new Transaction(null, TransactionType.INITIAL,request.getInitialCredit(),new Date(),account);
            account.getTransactions().add(transaction);

        }
        return converter.convert(accountRepository.save(account));
    }


}
