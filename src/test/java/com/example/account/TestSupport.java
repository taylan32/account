package com.example.account;


import com.example.account.dto.CreateAccountRequest;
import com.example.account.model.Customer;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestSupport {

    public static final String CUSTOMER_API_ENDPOINT = "/api/v1/customer/";
    public static final String ACCOUNT_API_ENDPOINT = "/api/v1/account";


    public Instant getCurrentInstant() {
        String instantExpected = "2023-01-16T20:18:30Z";
        Clock clock = Clock.fixed(Instant.parse(instantExpected), Clock.systemDefaultZone().getZone());
        return Instant.now(clock);
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.ofInstant(getCurrentInstant(), Clock.systemDefaultZone().getZone());
    }

    public Customer generateCustomer() {
        return new Customer("customer-id", "customer-name", "customer-surname", new ArrayList<>());
    }

    public CreateAccountRequest generateCreateAccountRequest(int initialCredit) {
        return new CreateAccountRequest("customer-id", new BigDecimal(initialCredit));
    }

    public CreateAccountRequest generateCreateAccountRequest(String customerId, int initialCredit) {
        return new CreateAccountRequest(customerId, new BigDecimal(initialCredit));
    }



 }
