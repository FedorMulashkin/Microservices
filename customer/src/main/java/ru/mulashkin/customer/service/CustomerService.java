package ru.mulashkin.customer.service;

import org.springframework.stereotype.Service;
import ru.mulashkin.customer.entity.Customer;
import ru.mulashkin.customer.repository.CustomerRepository;
import ru.mulashkin.customer.util.CustomerRegistrationRequest;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.save(customer);
    }
}
