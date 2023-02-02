package com.example.events.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
public class EventsRequest {
    public String customerId;
    public ZonedDateTime startTime;
    public ZonedDateTime endTime;
}
