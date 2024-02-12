-- liquibase formatted sql

--changeset victor_galkevich:1
ALTER TABLE categories
ADD COLUMN parent_id INTEGER REFERENCES categories(id);

--changeset victor_galkevich:2
ALTER TABLE lots
DROP COLUMN subcategory_id;

--changeset victor_galkevich:3
DROP TABLE subcategories;

--changeset victor_galkevich:4
ALTER TABLE lots
ADD COLUMN created_by VARCHAR(64),
ADD COLUMN modified_at TIMESTAMP,
ADD COLUMN modified_by VARCHAR(64);

--changeset victor_galkevich:5
ALTER TABLE locations
ALTER COLUMN id TYPE INTEGER;

--changeset victor_galkevich:6
ALTER TABLE categories
ALTER COLUMN id TYPE INTEGER;

--changeset victor_galkevich:7
ALTER TABLE lots
ALTER COLUMN category_id TYPE INTEGER,
ALTER COLUMN location_id TYPE INTEGER,
ALTER COLUMN expiration_date TYPE TIMESTAMP;

--changeset victor_galkevich:8
ALTER TABLE users
ADD COLUMN created_by VARCHAR(64),
ADD COLUMN modified_at TIMESTAMP,
ADD COLUMN modified_by VARCHAR(64);

--changeset victor_galkevich:9
ALTER TABLE users
ALTER COLUMN password TYPE VARCHAR(256);

--changeset victor_galkevich:10
ALTER TABLE users
ADD COLUMN phone_number VARCHAR(256);

--changeset victor_galkevich:11
ALTER TABLE users
DROP COLUMN password;



