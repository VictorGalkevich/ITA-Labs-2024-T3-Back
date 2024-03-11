-- liquibase formatted sql

--changeset karina-kuiko:1
ALTER TABLE lots
DROP COLUMN variety;

--changeset karina-kuiko:2
ALTER TABLE lots
ADD COLUMN currency VARCHAR(3);