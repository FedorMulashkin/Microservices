package ru.mulashkin.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mulashkin.clients.fraud.FraudCheckResponse;
import ru.mulashkin.clients.fraud.FraudClient;
import ru.mulashkin.clients.notification.NotificationClient;
import ru.mulashkin.clients.notification.NotificationRequest;
import ru.mulashkin.customer.entity.Customer;
import ru.mulashkin.customer.repository.CustomerRepository;
import ru.mulashkin.customer.util.CustomerRegistrationRequest;


@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getFirstName(),
                "hi");
        notificationClient.sendNotification(notificationRequest);
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }
        // todo: send notification
    }
}
