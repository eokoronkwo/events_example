package com.example.events.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventsResponse {
    String bucket;
    int total;

    @Override
    public String toString() {
        return String.format("%s bucket -> %s", bucket, total);
    }
}
