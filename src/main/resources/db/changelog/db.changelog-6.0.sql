-- liquibase formatted sql

--changeset victor_galkevich:1
create table bids
(
    id          BIGSERIAL,
    created_at  timestamp(6) with time zone,
    created_by  VARCHAR(32),
    modified_at timestamp(6) with time zone,
    modified_by VARCHAR(32),
    amount      bigint                 not null,
    currency    character varying(255) not null,
    lot_id      bigint                 not null,
    status      character varying(255) not null,
    user_id     bigint                 not null
);

--changeset victor_galkevich:2
ALTER TABLE lots
ADD COLUMN bid_quantity INTEGER,
ADD COLUMN total_price float8;

--changeset victor_galkevich:3
ALTER TABLE users
ADD COLUMN preferred_currency VARCHAR(3);

