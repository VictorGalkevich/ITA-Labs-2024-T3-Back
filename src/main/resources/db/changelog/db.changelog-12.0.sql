-- liquibase formatted sql

--changeset victor_galkevich:1
CREATE INDEX idx_country ON locations (country);

--changeset victor_galkevich:2
ALTER TABLE lots
DROP COLUMN location_id;

--changeset victor_galkevich:3
ALTER TABLE lots
ADD COLUMN country VARCHAR(64);

--changeset victor_galkevich:4
ALTER TABLE lots
ADD COLUMN region VARCHAR(64);
