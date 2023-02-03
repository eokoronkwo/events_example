package com.example.events.service;

import com.example.events.models.EventsRequest;
import com.example.events.models.EventsResponse;

import java.util.List;

public interface EventsService {
    List<EventsResponse> findByCustomerIdAndTimestampBetween(EventsRequest request);
}
