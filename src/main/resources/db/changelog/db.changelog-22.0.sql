-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE requests
ADD COLUMN title VARCHAR(255);