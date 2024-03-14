-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE lots
ADD COLUMN start_price float8;