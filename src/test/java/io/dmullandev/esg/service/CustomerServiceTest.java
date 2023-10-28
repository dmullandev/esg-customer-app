package io.dmullandev.esg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.dmullandev.esg.model.Customer;
import io.dmullandev.esg.repository.CustomerRepository;

public class CustomerServiceTest {

    private static final CustomerRepository customerRepository = mock(CustomerRepository.class);
    private static final CustomerService customerService = new CustomerService(customerRepository);

    private static final String TEST_USER_REF = "TEST_USER_REF";
    private static final String TEST_USER_NAME = "TEST_USER_NAME";

    @BeforeEach
    void setup() {
        given(customerRepository.getByCustomerRef(TEST_USER_REF)).willReturn(Optional.of(buildTestCustomer()));
    }

    @Test
    void GivenUserExist_WhenUserRepositoryGetUserByRef_ThenCustomerReturned() {
        Customer customer = customerService.getCustomer(TEST_USER_REF);

        verify(customerRepository).getByCustomerRef(TEST_USER_NAME);
        assertEquals(buildTestCustomer(), customer);
    }

    @Test
    void GivenUserNotExist_WhenUserRepositoryGetUserByRef_ThenNullReturned() {
        Customer customer = customerService.getCustomer("UNKNOWN_USER");

        verify(customerRepository).getByCustomerRef("UNKNOWN_USER");
        assertEquals(null, customer);
    }

    private Customer buildTestCustomer() {
        return Customer.builder()
                       .customerRef(TEST_USER_REF)
                       .customerName(TEST_USER_NAME)
                       .build();
    }

}
