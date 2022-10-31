package ru.mulashkin.notification.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mulashkin.clients.notification.NotificationRequest;
import ru.mulashkin.notification.entity.Notification;
import ru.mulashkin.notification.repository.NotificationRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest){
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerName())
                        .sender("mulashkin")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
