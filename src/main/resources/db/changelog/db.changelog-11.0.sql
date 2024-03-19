-- liquibase formatted sql

--changeset karina-kuiko:1
ALTER TABLE images
ADD COLUMN url varchar(255);

--changeset karina-kuiko:2
ALTER TABLE users
ADD COLUMN avatar_id BIGINT REFERENCES images (id);

--changeset karina-kuiko:3
ALTER TABLE images
ADD  COLUMN is_main_image BOOLEAN;