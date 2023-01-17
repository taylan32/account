package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String id;
    private BigDecimal balance;
    private LocalDateTime creationDate;
    private AccountCustomerDto customer;
    private List<TransactionDto> transactions;

}
