### Metronome Take-home Code Screen

Attached, find the file `events.csv`, which contains a log of events with the
the format customer\_id, event\_type, transaction\_id, timestamp.

Your task is to write a program that answers the following question:

> How many events did customer X send in the one hour buckets between timestamps A and B. 

So, for example, let's say you have the following usage events for a single customer:

- 2022-03-01T03:01:00Z event_1
- 2022-03-01T04:29:00Z event_2
- 2022-03-01T04:15:00Z event_3
- 2022-03-01T05:08:00Z event_4

If you sent start and end timestamps of Mar 1 3:00 am - Mar 1 6:00 am, we’d expect to see these output values (format is up to you):
- 2022-03-01T03:00:00Z bucket -> 1
- 2022-03-01T04:00:00Z bucket -> 2
- 2022-03-01T05:00:00Z bucket -> 1

Choice of language, platform, and libraries is left up to you, as long as the
person evaluating your submission doesn't have to think too hard to figure out
how to run it. We all use recent macOS.

We expect this exercise to take 1-3 hours.

*Bonus:* Include an HTTP service that answers the same question.
