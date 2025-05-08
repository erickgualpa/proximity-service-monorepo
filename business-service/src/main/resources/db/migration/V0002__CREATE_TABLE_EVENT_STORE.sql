CREATE TABLE event_store (
    id UUID PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    version VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    entity_id UUID NOT NULL,
    event JSONB NOT NULL
);
ALTER TABLE event_store OWNER TO debezium_user;