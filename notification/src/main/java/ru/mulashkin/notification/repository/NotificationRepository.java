package ru.mulashkin.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mulashkin.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
