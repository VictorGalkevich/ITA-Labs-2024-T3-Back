-- liquibase formatted sql

--changeset karina-kuiko:1
ALTER TABLE categories
ADD COLUMN image_id uuid REFERENCES images(id);