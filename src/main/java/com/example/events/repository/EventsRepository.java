package com.example.events.repository;

import com.example.events.entity.Events;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface EventsRepository extends org.springframework.data.repository.Repository<Events, Long> {
    List<Events> findByCustomerIdAndTimestampBetween(String customerId, ZonedDateTime startTime, ZonedDateTime endTime);
}
