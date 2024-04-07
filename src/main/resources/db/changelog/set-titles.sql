-- liquibase formatted sql

--changeset victor_galkevich:1
UPDATE requests SET title = 'Oranges wanted' WHERE category_id = 9;

--changeset victor_galkevich:2
UPDATE requests SET title = 'Apples wanted' WHERE category_id = 7;