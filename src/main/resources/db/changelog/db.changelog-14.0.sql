-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE lots
ALTER COLUMN total_price TYPE BIGINT;

--changeset victor_galkevich:2
ALTER TABLE lots
ALTER COLUMN start_price TYPE BIGINT;
