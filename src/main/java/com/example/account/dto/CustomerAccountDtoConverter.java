package com.example.account.dto;

import com.example.account.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {

    private final TransactionDtoConverter transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public CustomerAccountDto convert(Account from) {
        return new CustomerAccountDto(
                from.getId(),
                from.getBalance(),
                from.getTransactions().stream().map(transactionDtoConverter::convert).collect(Collectors.toList()),
                from.getCreationDate()
        );
    }

}
