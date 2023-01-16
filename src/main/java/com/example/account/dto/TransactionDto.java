package com.example.account.dto;

import com.example.account.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String id;
    private TransactionType transactionType;
    private Long amount;
    private Date transactionDate;

}
