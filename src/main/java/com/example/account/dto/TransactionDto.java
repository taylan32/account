package com.example.account.dto;

import com.example.account.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String id;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

}
