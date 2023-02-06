package com.example.events.service.impl;

import com.example.events.entity.Events;
import com.example.events.model.EventsRequest;
import com.example.events.model.EventsResponse;
import com.example.events.repository.EventsRepository;
import com.example.events.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public List<EventsResponse> findByCustomerIdAndTimestampBetween(EventsRequest request) {
        var requestZone = request.getStartTime().getZone();
        var startTime = request.getStartTime();
        var endTime = request.getEndTime();
        if (endTime.isBefore(startTime)) {
            return new ArrayList<>();
        }
        var eventsList = eventsRepository.findByCustomerIdAndTimestampBetweenOrderByTimestampAsc(
                request.getCustomerId(), request.getStartTime(), request.getEndTime());
        var timestampBucketList = new ArrayList<EventsResponse>();
        var currentBucketTotal = 0;
        var currentTime = startTime.withMinute(0).withSecond(0);
//        System.out.println("initial current time");
//        System.out.println(currentTime);
        var nextCurrentTime = startTime.plusHours(1).withMinute(0).withSecond(0);
//        System.out.println("initial next current time");
//        System.out.println(nextCurrentTime);
        for (Events event: eventsList) {
//            System.out.println("event timestamp");
//            System.out.println(event.getTimestamp());
            if (event.getTimestamp().withZoneSameInstant(requestZone).isBefore(nextCurrentTime)) {
//                System.out.println("adding to total");
                currentBucketTotal += 1;
//                System.out.println("total is now");
//                System.out.println(currentBucketTotal);
                continue;
            }
            timestampBucketList.add(new EventsResponse(
                    currentTime.toOffsetDateTime().truncatedTo( ChronoUnit.MINUTES ).toString(),
                    currentBucketTotal));
            currentTime = nextCurrentTime;
//            System.out.println("new current time");
//            System.out.println(currentTime);
            nextCurrentTime = currentTime.plusHours(1);
//            System.out.println("new next current time");
//            System.out.println(nextCurrentTime);
            currentBucketTotal = 0;
        }
        timestampBucketList.add(new EventsResponse(
                currentTime.toOffsetDateTime().truncatedTo( ChronoUnit.MINUTES ).toString(),
                currentBucketTotal));
//        System.out.println("end list");
//        System.out.println(timestampBucketList);
        return timestampBucketList;
    }
}

/* TODO remove response object
*   make response list of strings ? or better object for unit testing?
* make validator for request
* look up using h2 db in testing for either importing or setting data in database
* validator should throw two exceptions with messages, missing or null field or startime > endtime
* unit test should test against empty input
* test against test input to give correct number of timestamps to a specific bucket
* test against total number of buckets created
* test against customerID not found? - should it give back message? or is this more of a DB test
* test against no event with in specific range for valid customerID */