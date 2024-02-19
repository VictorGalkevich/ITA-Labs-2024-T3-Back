-- liquibase formatted sql

--changeset karina_kuiko:1
ALTER TABLE lots
DROP COLUMN image_url;

--changeset karina_kuiko:2
CREATE TABLE IF NOT EXISTS images (
                                      id              BIGSERIAL PRIMARY KEY,
                                      name            VARCHAR(255) UNIQUE  NOT NULL,
    lot_id     BIGINT REFERENCES lots (id)
    );