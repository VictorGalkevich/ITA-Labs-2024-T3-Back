-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE lots
ADD COLUMN length_unit VARCHAR(32);