package ru.mulashkin.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mulashkin.customer.entity.Customer;
import ru.mulashkin.customer.repository.CustomerRepository;
import ru.mulashkin.customer.util.CustomerRegistrationRequest;
import ru.mulashkin.customer.util.FraudCheckResponse;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        // todo: check if fraudster
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8082/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId());
        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }
        // todo: send notification
    }
}
