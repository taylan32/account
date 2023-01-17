package com.example.account.service;

import com.example.account.TestSupport;
import com.example.account.dto.CustomerDto;
import com.example.account.dto.CustomerDtoConverter;
import com.example.account.exception.CustomerNotFoundException;
import com.example.account.model.Customer;
import com.example.account.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerServiceTest extends TestSupport {
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerDtoConverter.class);
        customerService = new CustomerService(customerRepository, converter);

    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = generateCustomer();

        Mockito.when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
        Customer result = customerService.findCustomerById("customer-id");
        assertEquals(customer, result);

    }


    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById("id"));
    }



    @Test
    public void getCustomerById_whenCustomerIdExists_shouldReturnCustomerDto() {
        Customer customer = generateCustomer();
        CustomerDto customerDto = new CustomerDto("customer-id","customer-name","customer-surname",new ArrayList<>());

        Mockito.when(customerRepository.findById("customer-id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomerById("customer-id");

        assertEquals(customerDto, result);

    }

    @Test
    public void getCustomerById_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException() {

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById("id"));

        // converterin hiç bir methodunu çağırma.--> hata üretildi
        Mockito.verifyNoInteractions(converter);

    }

}
