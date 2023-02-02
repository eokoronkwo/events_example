package com.example.events.controller;

import com.example.events.entity.Events;
import com.example.events.models.EventsRequest;
import com.example.events.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/events")
    @ResponseBody
    public List<Events> fetchEventsByCustomerIdAndTime(@RequestBody EventsRequest request) {
        return eventsService.findByCustomerIdAndTimestampBetween(request);
    }
}
