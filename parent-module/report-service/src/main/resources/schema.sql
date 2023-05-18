
create table if not exists ack_message (id integer not null, service_id bigint not null, alert_id integer, primary key (id))
create table if not exists alert (id serial not null, ack_message_id integer, event_id integer, primary key (id))
create table if not exists event (id serial not null, status smallint, event_type_id integer, primary key (id))
create table if not exists event_type (id integer not null, primary key (id))
create table if not exists event_type_agent_list (event_type_id integer not null, agent_name varchar(255))