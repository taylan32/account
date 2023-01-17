package com.example.account;

import com.example.account.dto.CustomerDtoConverter;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class IntegrationTestSupport extends TestSupport {

    @Autowired
    public MockMvc mockMvc;


    @Autowired
    public CustomerRepository customerRepository;


    @Autowired
    public CustomerDtoConverter converter;

    @Autowired
    public AccountService accountService;


    public final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }


}
