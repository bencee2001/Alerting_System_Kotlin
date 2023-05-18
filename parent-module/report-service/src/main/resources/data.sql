TRUNCATE TABLE ack_message CASCADE;
TRUNCATE TABLE alert CASCADE;
TRUNCATE TABLE event CASCADE;
TRUNCATE TABLE event_type CASCADE;
TRUNCATE TABLE event_type_agent_list CASCADE;

INSERT INTO event_type (id)
VALUES (1),(2),(3);

INSERT INTO event_type_agent_list (event_type_id, agent_name)
VALUES
    (1,'police'),
    (1,'hospital'),
    (2,'fireDep'),
    (3,'police');