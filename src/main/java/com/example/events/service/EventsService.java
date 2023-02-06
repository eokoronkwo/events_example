package com.example.events.service;

import com.example.events.model.EventsRequest;
import com.example.events.model.EventsResponse;

import java.util.List;

public interface EventsService {
    List<EventsResponse> findByCustomerIdAndTimestampBetween(EventsRequest request);
}
