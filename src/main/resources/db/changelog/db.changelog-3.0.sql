-- liquibase formatted sql

--changeset karina-kuiko:1
ALTER TABLE lots
ADD COLUMN weight VARCHAR(32);

--changeset karina-kuiko:2
ALTER TABLE lots
ALTER COLUMN size TYPE INTEGER;