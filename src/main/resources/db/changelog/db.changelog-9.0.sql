-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE lots
ADD COLUMN start_price float8;

--changeset victor_galkevich:2
ALTER TABLE bids
ALTER COLUMN created_by TYPE VARCHAR(128);

--changeset victor_galkevich:3
ALTER TABLE bids
ALTER COLUMN modified_by TYPE VARCHAR(128);

