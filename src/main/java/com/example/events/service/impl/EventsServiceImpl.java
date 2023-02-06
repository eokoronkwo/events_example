package com.example.events.service.impl;

import com.example.events.entity.Events;
import com.example.events.model.EventsRequest;
import com.example.events.model.EventsResponse;
import com.example.events.repository.EventsRepository;
import com.example.events.service.EventsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EventsServiceImpl implements EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    /*
    Returns a list of EventsResponses in buckets with timestamps incremented by hour.
    Hour buckets that have zero events will automatically be truncated from the beginning,
    end, and skipped in the middle.
     */
    @Override
    public List<EventsResponse> findByCustomerIdAndTimestampBetween(EventsRequest request) {
        var requestZone = request.getStartTime().getZone();
        var eventsList = eventsRepository.findByCustomerIdAndTimestampBetweenOrderByTimestampAsc(
                request.getCustomerId(), request.getStartTime(), request.getEndTime());
        if (eventsList.size() == 0) {
            return new ArrayList<>();
        }
        var timestampBucketList = new ArrayList<EventsResponse>();
        var currentBucketTotal = 0;
        var currentTime = eventsList.get(0).getTimestamp().withMinute(0).withSecond(0);
        var nextCurrentTime = currentTime.plusHours(1).withMinute(0).withSecond(0);
        for (Events event: eventsList) {
            if (event.getTimestamp().withZoneSameInstant(requestZone).isBefore(nextCurrentTime)) {
                currentBucketTotal += 1;
                continue;
            }
            timestampBucketList.add(new EventsResponse(
                    currentTime.toOffsetDateTime().truncatedTo( ChronoUnit.MINUTES ).toString(),
                    currentBucketTotal));
            currentTime = event.getTimestamp().withMinute(0).withSecond(0);
            nextCurrentTime = currentTime.plusHours(1);
            currentBucketTotal = 1;
        }
        timestampBucketList.add(new EventsResponse(
                currentTime.toOffsetDateTime().truncatedTo( ChronoUnit.MINUTES ).toString(),
                currentBucketTotal));
        return timestampBucketList;
    }
}