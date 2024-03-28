-- liquibase formatted sql

--changeset victor_galkevich:1
CREATE TABLE offers
(
    id                  bigserial
        primary key,
    created_at          timestamp(6) with time zone,
    created_by          varchar(255),
    modified_at         timestamp(6) with time zone,
    status              VARCHAR(255),
    modified_by         varchar(255),
    purchase_request_id bigint not null,
    lot_id              bigint not null
);

--changeset victor_galkevich:2
CREATE TABLE requests
(
    id              BIGSERIAL
        PRIMARY KEY,
    currency        VARCHAR(255) not null
        constraint requests_currency_check
            check ((currency)::text = ANY
                   ((ARRAY ['USD'::character varying, 'EUR'::character varying, 'BYN'::character varying])::text[])),
    description     varchar(255),
    desired_price   bigint       not null,
    status          varchar(255) not null
        constraint requests_status_check
            check ((status)::text = ANY
                   ((ARRAY ['ACTIVE'::character varying, 'SOLD'::character varying, 'MODERATED'::character varying, 'CANCELLED'::character varying, 'AUCTION_ENDED'::character varying, 'EXPIRED'::character varying])::text[])),
    expiration_date timestamp(6) with time zone,
    reject_message  VARCHAR(255),
    offer_quantity  INTEGER,
    category_id INTEGER references categories(id),
    user_id uuid references users(id)
);

--changeset victor_galkevich:3
ALTER TABLE lots
ADD COLUMN reject_message VARCHAR(255);




