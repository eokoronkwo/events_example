package com.example.events.service;

import com.example.events.models.EventsRequest;

import java.util.List;

public interface EventsService {
    List<String> findByCustomerIdAndTimestampBetween(EventsRequest request);
}
