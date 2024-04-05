-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE lots
ALTER COLUMN from_size SET NOT NULL;

--changeset victor_galkevich:2
ALTER TABLE lots
ALTER COLUMN to_size SET NOT NULL;