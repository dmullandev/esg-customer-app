package io.dmullandev.esg.service;

import org.springframework.stereotype.Service;
import io.dmullandev.esg.model.Customer;
import io.dmullandev.esg.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void createCustomer(Customer customer) {
        log.info("Customer with id {} saved.", customerRepository.saveAndFlush(customer).getId());
    }

    public Customer getCustomer(String customerRef) {
        return customerRepository.getByCustomerRef(customerRef).orElse(null);
    }
}
