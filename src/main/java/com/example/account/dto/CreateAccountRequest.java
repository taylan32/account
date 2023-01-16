package com.example.account.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountRequest {

    @NotBlank(message = "Customer id is not allowed to be empty")
    private String customerId;

    @Min(value = 0, message = "Credit can not be less then 0")
    private Long initialCredit;
}
