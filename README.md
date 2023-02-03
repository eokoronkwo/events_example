### Events Query Example Project

The file `events.csv` contains a log of events with the 
format customer_id, event_type, transaction_id, timestamp.

Events are automatically imported into an H2 Database upon each application start.

This program executes the following ask:

> How many events did customer X send in one hour buckets between timestamps A and B. 

Example following events for a single customer:

- 2022-03-01T03:01:00Z event_1
- 2022-03-01T04:29:00Z event_2
- 2022-03-01T04:15:00Z event_3
- 2022-03-01T05:08:00Z event_4

With start and end timestamps of Mar 1 3:00 am - Mar 1 6:00 am, this is the output:
- 2022-03-01T03:00:00Z bucket -> 1
- 2022-03-01T04:00:00Z bucket -> 2
- 2022-03-01T05:00:00Z bucket -> 1

After 

{
"customerId":"30330c9c4e7173ba9474c46ee5191570",
"startTime":"2021-03-01T00:01:35.259+00",
"endTime":"2021-03-01T00:04:36.259+00"
}

