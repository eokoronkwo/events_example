package com.example.events.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "events")
public class Events {
    @Column(name = "customer_id")
    public String customerId;
    @Column(name = "event_type")
    public String eventType;
    @Id
    @Column(name = "transaction_id")
    public String transactionId;
    @Column(name = "timestamp")
    public ZonedDateTime timestamp;
}
