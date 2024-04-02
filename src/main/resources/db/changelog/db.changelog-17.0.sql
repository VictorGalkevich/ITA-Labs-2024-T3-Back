-- liquibase formatted sql

--changeset karina-kuiko:1
ALTER TABLE lots
RENAME COLUMN size TO from_size;

--changeset karina-kuiko:2
ALTER TABLE lots
ADD COLUMN to_size INT;