-- liquibase formatted sql

--changeset karina-kuiko:1
ALTER TABLE lots
ADD COLUMN user_id uuid REFERENCES users(id);