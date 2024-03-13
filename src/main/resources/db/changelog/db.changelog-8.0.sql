-- liquibase formatted sql

--changeset victor_galkevich:1
TRUNCATE TABLE bids;

--changeset victor_galkevich:2
TRUNCATE TABLE users;

--changeset victor_galkevich:3
ALTER TABLE bids
DROP COLUMN user_id;

--changeset victor_galkevich:4
ALTER TABLE users
DROP COLUMN id;

--changeset victor_galkevich:5
ALTER TABLE bids
ADD COLUMN user_id uuid;

--changeset victor_galkevich:6
ALTER TABLE users
ADD COLUMN id uuid PRIMARY KEY;

