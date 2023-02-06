package com.example.events.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@Data
public class EventsRequest {

    @NotEmpty(message = "Customer ID is required.")
    public String customerId;

    @NotNull(message = "Start timestamp is required.")
    public ZonedDateTime startTime;

    @NotNull(message = "End timestamp is required.")
    public ZonedDateTime endTime;
}
