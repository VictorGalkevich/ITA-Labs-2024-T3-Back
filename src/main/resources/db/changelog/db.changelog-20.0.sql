-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE lots
ADD COLUMN buyer_id uuid references users(id);