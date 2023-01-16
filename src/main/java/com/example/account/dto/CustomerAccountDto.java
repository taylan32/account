package com.example.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccountDto {

    private String id;
    private Long balance;
    private List<TransactionDto> transactions;
    private Date creationTime;

}
