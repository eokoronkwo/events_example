package com.example.events.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventsResponse {
    String bucket;
    int total;

}
