-- liquibase formatted sql

--changeset karina-kuiko:1
ALTER TABLE categories
DROP COLUMN image_id;

--changeset karina-kuiko:2
ALTER TABLE categories
ADD COLUMN image_url VARCHAR (255);