package ru.mulashkin.notification.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cotification_id")
    private Integer notificationId;
    @Column(name = "to_customer_id")
    private Integer toCustomerId;
    @Column(name = "to_customer_email")
    private String toCustomerEmail;
    @Column(name = "sender")
    private String sender;
    @Column(name = "message")
    private String message;
    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}
