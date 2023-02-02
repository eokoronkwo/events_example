CREATE TABLE events (
      customer_id VARCHAR(128),
      event_type VARCHAR(128),
      transaction_id VARCHAR(128),
      timestamp TIMESTAMP WITH TIME ZONE,
      PRIMARY KEY (transaction_id)
) AS SELECT * FROM CSVREAD('events.csv');