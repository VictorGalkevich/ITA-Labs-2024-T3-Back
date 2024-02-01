-- liquibase formatted sql

--changeset karina_kuiko:1
CREATE TABLE IF NOT EXISTS categories (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(64) UNIQUE  NOT NULL
);

--changeset karina_kuiko:2
CREATE TABLE IF NOT EXISTS subcategories (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(64) UNIQUE  NOT NULL,
    category_id     BIGINT REFERENCES categories (id)
);

--changeset karina_kuiko:3
CREATE TABLE IF NOT EXISTS locations (
    id              BIGSERIAL PRIMARY KEY,
    country         VARCHAR(64)         NOT NULL,
    region          VARCHAR(64)         NOT NULL
);

--changeset karina_kuiko:4
CREATE TABLE IF NOT EXISTS lots (
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255)       NOT NULL,
    category_id     BIGINT REFERENCES categories (id),
    subcategory_id  BIGINT REFERENCES subcategories (id),
    variety         VARCHAR(64)        NOT NULL,
    quantity        BIGINT             NOT NULL,
    price_per_unit  DOUBLE PRECISION   NOT NULL,
    location_id     BIGINT REFERENCES locations (id),
    description     VARCHAR(255)       NOT NULL,
    status          VARCHAR(32)        NOT NULL,
    image_url       VARCHAR(255)       NOT NULL,
    expiration_date DATE               NOT NULL,
    size            VARCHAR(32)        NOT NULL,
    packaging       VARCHAR(32)        NOT NULL,
    created_at      TIMESTAMP          NOT NULL
);

--changeset karina_kuiko:5
CREATE TABLE IF NOT EXISTS users (
    id              BIGSERIAL PRIMARY KEY,
    created_at      TIMESTAMP          NOT NULL,
    first_name      VARCHAR(64)        NOT NULL,
    last_name       VARCHAR(64)        NOT NULL,
    email           VARCHAR(64) UNIQUE NOT NULL,
    role            VARCHAR(32)        NOT NULL,
    password        VARCHAR(64)        NOT NULL
);