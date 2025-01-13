create table if not exists outbox_event (
    id           uuid                     primary key,
    created_at   timestamp with time zone not null,
    published_at timestamp with time zone,
    topic        varchar(255)             not null,
    payload      text
);