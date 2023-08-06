CREATE KEYSPACE chatdb WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 3 };

USE chatdb;

CREATE TABLE chat (
     conversation_id uuid,
     sender_id int,
     receiver_id int,
     timestamp timestamp,
     message text,
     message_type text,
     PRIMARY KEY ((conversation_id), timestamp)
) WITH CLUSTERING ORDER BY (timestamp DESC);


CREATE TABLE conversation_lookup (
     sender_id int,
     receiver_id int,
     conversation_id uuid,
     PRIMARY KEY ((sender_id, receiver_id))
);
