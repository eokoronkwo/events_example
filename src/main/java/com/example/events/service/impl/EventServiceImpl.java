package com.example.events.service.impl;

import com.example.events.entity.Events;
import com.example.events.models.EventsRequest;
import com.example.events.repository.EventsRepository;
import com.example.events.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public List<Events> findByCustomerIdAndTimestampBetween(EventsRequest request) {
        if (request.getEndTime().isBefore(request.getStartTime())) {
            new ArrayList<>();
        }
        return eventsRepository.findByCustomerIdAndTimestampBetween(request.getCustomerId(), request.getStartTime(), request.getEndTime());
    }
}
