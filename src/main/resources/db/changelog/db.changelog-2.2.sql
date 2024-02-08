-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE users
ADD COLUMN phone_number VARCHAR(256);