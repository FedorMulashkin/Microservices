package ru.mulashkin.fraud.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fraud_check_history")
public class FraudCheckHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "is_fraudster")
    private Boolean isFraudster;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
