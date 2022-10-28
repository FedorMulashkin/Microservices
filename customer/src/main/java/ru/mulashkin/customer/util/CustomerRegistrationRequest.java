package ru.mulashkin.customer.util;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
