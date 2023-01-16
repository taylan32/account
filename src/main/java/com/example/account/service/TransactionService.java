package com.example.account.service;

import com.example.account.model.Account;
import com.example.account.model.Transaction;
import com.example.account.model.TransactionType;
import com.example.account.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionService {

    private Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    protected Transaction initiateMoney(final Account account, Long amount) {
        return transactionRepository.save(new Transaction(null, TransactionType.INITIAL, amount, new Date(), account));
    }

}
