package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String id;
    private Long balance;
    private Date creationDate;
    private AccountCustomerDto customer;
    private List<TransactionDto> transactions;

}
