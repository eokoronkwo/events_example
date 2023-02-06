package com.example.events.service;

import com.example.events.model.EventsRequest;
import com.example.events.repository.EventsRepository;
import com.example.events.service.impl.EventsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class EventsServiceTests {

    @Resource
    private EventsRepository eventsRepository;

    private EventsServiceImpl employeeService;


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        employeeService = new EventsServiceImpl(eventsRepository);
    }

    @Test
    public void getEventsForCustomerReturnsCorrectResponse() throws JsonProcessingException {
        var startTime = ZonedDateTime.parse("2021-02-28T00:01:35.259+00");
        var endTime = ZonedDateTime.parse("2021-03-01T10:01:36.259+00");

        var testRequest = new EventsRequest("1", startTime, endTime);

        var testResponse = employeeService.findByCustomerIdAndTimestampBetween(testRequest);

        assertEquals(4, testResponse.size());
        int totalEvents = testResponse.stream()
                .map(obj -> obj.getTotal())
                .reduce(0, (subtotal, element) -> subtotal + element);
        assertEquals(11, totalEvents);
        assertEquals(3, testResponse.get(0).getTotal());
    }

    @Test
    public void getEventsForCustomerNotInDb() throws JsonProcessingException {
        var startTime = ZonedDateTime.parse("2021-02-28T00:01:35.259+00");
        var endTime = ZonedDateTime.parse("2021-03-01T10:01:36.259+00");

        var testRequest = new EventsRequest("2", startTime, endTime);

        var testResponse = employeeService.findByCustomerIdAndTimestampBetween(testRequest);

        assertEquals(0, testResponse.size());
    }

    @Test
    public void getEventsForCustomerNoEventWithinTimestampRange() throws JsonProcessingException {
        var startTime = ZonedDateTime.parse("2021-02-27T00:01:35.259+00");
        var endTime = ZonedDateTime.parse("2021-02-27T10:01:36.259+00");

        var testRequest = new EventsRequest("1", startTime, endTime);

        var testResponse = employeeService.findByCustomerIdAndTimestampBetween(testRequest);

        assertEquals(0, testResponse.size());
    }



}
