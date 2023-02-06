package com.example.events.controller;

import com.example.events.model.EventsRequest;
import com.example.events.model.EventsResponse;
import com.example.events.service.EventsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/events")
    @ResponseBody
    public List<EventsResponse> fetchEventsByCustomerIdAndTime(@Valid @RequestBody EventsRequest request) {
        return eventsService.findByCustomerIdAndTimestampBetween(request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        var errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return errors;
    }
}
