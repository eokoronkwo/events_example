package com.example.events.repository;

import com.example.events.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
    List<Events> findByCustomerIdAndTimestampBetweenOrderByTimestampAsc(String customerId, ZonedDateTime startTime, ZonedDateTime endTime);
}
