-- liquibase formatted sql

--changeset victor_galkevich:1
CREATE TABLE rates
(
    id       bigserial,
    buy_byn  float8,
    sell_byn float8,
    buy_eur  float8,
    sell_eur float8
);

--changeset victor_galkevich:2
INSERT INTO rates (buy_byn, sell_byn, buy_eur, sell_eur) VALUES (3.25, 0.307, 0.9306, 1.074);

--changeset victor_galkevich:3
alter table if exists requests
add column length_unit varchar(255) not null check (length_unit in ('CENTIMETER','MILLIMETER')),
add column country varchar(255),
add column region varchar(255),
add column packaging varchar(255) not null check (packaging in ('BOX','BASKET','CARTON','BAG','CRATE','BOTTLE','BUNCH','SACK')),
add column price_per_unit float(53) not null,
add column quantity bigint not null,
add column size integer not null,
add column weight varchar(255) not null check (weight in ('TON','KILOGRAM','PCS'));

--changeset victor_galkevich:4
alter table if exists offers
add constraint FKfdwqp31x14oyn47bblejyx005
        foreign key (lot_id)
            references lots;

--changeset victor_galkevich:5
ALTER TABLE bids
ALTER COLUMN amount TYPE float8;

--changeset victor_galkevich:6
ALTER TABLE lots
ALTER COLUMN start_price TYPE float8,
ALTER COLUMN total_price TYPE float8;

--changeset victor_galkevich:7
ALTER TABLE requests
ALTER COLUMN desired_price TYPE float8;
