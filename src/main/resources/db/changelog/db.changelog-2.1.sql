-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE users
ALTER COLUMN password TYPE VARCHAR(256);