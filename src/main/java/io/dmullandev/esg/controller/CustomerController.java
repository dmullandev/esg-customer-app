package io.dmullandev.esg.controller;

import static io.dmullandev.esg.constants.EsgAppConstants.ENDPOINTURL_CUSTOMER_CONTROLLER_V1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.dmullandev.esg.model.Customer;
import io.dmullandev.esg.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(ENDPOINTURL_CUSTOMER_CONTROLLER_V1)
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void createCustomer(@RequestBody Customer customer) {
        log.info("Create request for: {}", customer);
        customerService.createCustomer(customer);
    }

    @GetMapping(path = "{customerId}")
    public Customer getCustomer(@PathVariable("customerId") String customerRef) {
        log.info("Customer Information request for reference {}", customerRef);
        return customerService.getCustomer(customerRef);
    }
}
