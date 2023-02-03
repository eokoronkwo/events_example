package com.example.events.service.impl;

import com.example.events.entity.Events;
import com.example.events.models.EventsRequest;
import com.example.events.models.EventsResponse;
import com.example.events.repository.EventsRepository;
import com.example.events.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public List<EventsResponse> findByCustomerIdAndTimestampBetween(EventsRequest request) {
        var startTime = request.getStartTime().withZoneSameInstant(ZoneId.systemDefault());
        var endTime = request.getEndTime().withZoneSameInstant(ZoneId.systemDefault());
        if (endTime.isBefore(startTime)) {
            return new ArrayList<>();
        }
        var eventsList = eventsRepository.findByCustomerIdAndTimestampBetweenOrderByTimestampAsc(
                request.getCustomerId(), request.getStartTime(), request.getEndTime());
        var timestampBucketList = new ArrayList<EventsResponse>();
        var currentBucketTotal = 0;
        var currentTime = startTime.withMinute(0).withSecond(0);
        System.out.println("initial current time");
        System.out.println(currentTime);
        var nextCurrentTime = startTime.plusHours(1).withMinute(0).withSecond(0);
        System.out.println("initial next current time");
        System.out.println(nextCurrentTime);
        for (Events event: eventsList) {
            System.out.println("event timestamp");
            System.out.println(event.getTimestamp());
            if (event.getTimestamp().isBefore(nextCurrentTime)) {
                System.out.println("adding to total");
                currentBucketTotal += 1;
                System.out.println("total is now");
                System.out.println(currentBucketTotal);
                continue;
            }
            timestampBucketList.add(new EventsResponse(currentTime.toString(), currentBucketTotal));
            currentTime = nextCurrentTime;
            System.out.println("new current time");
            System.out.println(currentTime);
            nextCurrentTime = currentTime.plusHours(1);
            System.out.println("new next current time");
            System.out.println(nextCurrentTime);
            currentBucketTotal = 0;
        }
        timestampBucketList.add(new EventsResponse(currentTime.toString(), currentBucketTotal));
        System.out.println("end list");
        System.out.println(timestampBucketList);
        return timestampBucketList;
    }
}
