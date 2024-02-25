-- liquibase formatted sql

--changeset victor_galkevich:1
CREATE TABLE IF NOT EXISTS bids (
    id BIGSERIAL primary key,
    created_at TIMESTAMP,
    created_by VARCHAR(64),
    modified_at TIMESTAMP,
    modified_by VARCHAR(64),
    status VARCHAR(64) not null,
    lot_id BIGINT references lots(id) not null,
    user_id BIGINT references users(id) not null,
    amount BIGINT not null,
    currency VARCHAR(10) not null
);

--changeset victor_galkevich:2
ALTER TABLE lots
ADD COLUMN bid_id BIGINT references bids(id);