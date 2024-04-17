-- liquibase formatted sql

--changeset victor_galkevich:1
INSERT INTO requests
(currency, description, desired_price, status, expiration_date, reject_message, offer_quantity, category_id, user_id, length_unit, country, region, packaging, price_per_unit, quantity, size, weight)
VALUES
    ('USD', 'I want Super delicious oranges', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Paris', 'BOX', 133.33333333333334, 75, 10, 'TON'),
    ('EUR', 'I need mega big apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'CRATE', 20.0, 25, 10, 'PCS'),
    ('BYN', 'Looking for Super tasty oranges', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Sochi', 'CRATE', 80.0, 50, 30, 'KILOGRAM'),
    ('EUR', 'Willing to buy apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Paris', 'BAG', 5.0, 100, 10, 'PCS'),
    ('USD', 'I need mega big apples', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Bordeaux', 'BOTTLE', 133.33333333333334, 75, 30, 'TON'),
    ('EUR', 'I want Super delicious oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Paris', 'CARTON', 20.0, 25, 30, 'PCS'),
    ('USD', 'I need mega big apples', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Sochi', 'SACK', 100.0, 100, 10, 'TON'),
    ('EUR', 'I want Super delicious oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Moscow', 'BOTTLE', 5.0, 100, 30, 'PCS'),
    ('USD', 'Looking for Super tasty oranges', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Gomel', 'BUNCH', 133.33333333333334, 75, 10, 'TON'),
    ('USD', 'I need mega big apples', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Bordeaux', 'BASKET', 200.0, 50, 30, 'TON'),
    ('BYN', 'Yummy apples for needed', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Paris', 'BOX', 53.333333333333336, 75, 30, 'KILOGRAM'),
    ('EUR', 'In need of Super old apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'BAG', 10.0, 50, 10, 'PCS'),
    ('EUR', 'I want Super delicious oranges', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Moscow', 'BOX', 50.0, 10, 10, 'PCS'),
    ('EUR', 'Mega yummy oranges needed', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Marseille', 'BASKET', 20.0, 25, 10, 'PCS'),
    ('EUR', 'Yummy apples for needed', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Minsk', 'CRATE', 20.0, 25, 30, 'PCS'),
    ('EUR', 'I need mega big apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Moscow', 'CARTON', 5.0, 100, 10, 'PCS'),
    ('USD', 'I need mega big apples', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Brest', 'BOX', 200.0, 50, 30, 'TON'),
    ('USD', 'Yummy apples for needed', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Paris', 'BUNCH', 100.0, 100, 10, 'TON'),
    ('BYN', 'Mega yummy oranges needed', 4000, 'MODERATED', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Paris', 'BAG', 53.333333333333336, 75, 10, 'KILOGRAM'),
    ('USD', 'I want Super delicious oranges', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Marseille', 'BOTTLE', 133.33333333333334, 75, 30, 'TON'),
    ('USD', 'Willing to buy apples', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Marseille', 'BAG', 1000.0, 10, 10, 'TON'),
    ('USD', 'I want Super delicious oranges', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Minsk', 'BOTTLE', 100.0, 100, 30, 'TON'),
    ('USD', 'Willing to buy apples', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Brest', 'BUNCH', 400.0, 25, 30, 'TON'),
    ('BYN', 'Yummy apples for needed', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Marseille', 'BUNCH', 160.0, 25, 30, 'KILOGRAM'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'BAG', 20.0, 25, 10, 'PCS'),
    ('EUR', 'In need of Super old apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Minsk', 'CRATE', 10.0, 50, 30, 'PCS'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Bordeaux', 'BOX', 20.0, 25, 30, 'PCS'),
    ('BYN', 'I need mega big apples', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Minsk', 'BASKET', 40.0, 100, 30, 'KILOGRAM'),
    ('EUR', 'I want Super delicious oranges', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Moscow', 'BAG', 6.666666666666667, 75, 10, 'PCS'),
    ('USD', 'Looking for Super tasty oranges', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Sochi', 'CARTON', 200.0, 50, 10, 'TON'),
    ('USD', 'I need mega big apples', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Saint-Petersburg', 'CRATE', 100.0, 100, 30, 'TON'),
    ('EUR', 'In need of Super old apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Bordeaux', 'CRATE', 6.666666666666667, 75, 30, 'PCS'),
    ('EUR', 'Mega yummy oranges needed', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Sochi', 'BAG', 5.0, 100, 10, 'PCS'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Brest', 'BOTTLE', 20.0, 25, 30, 'PCS'),
    ('USD', 'Willing to buy apples', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Sochi', 'BAG', 133.33333333333334, 75, 30, 'TON'),
    ('USD', 'Willing to buy apples', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'CRATE', 133.33333333333334, 75, 10, 'TON'),
    ('USD', 'Willing to buy apples', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Moscow', 'BASKET', 200.0, 50, 10, 'TON'),
    ('EUR', 'Yummy apples for needed', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Sochi', 'CRATE', 20.0, 25, 30, 'PCS'),
    ('EUR', 'Willing to buy apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Saint-Petersburg', 'BOX', 10.0, 50, 30, 'PCS'),
    ('BYN', 'Looking for Super tasty oranges', 4000, 'MODERATED', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Bordeaux', 'BUNCH', 40.0, 100, 10, 'KILOGRAM'),
    ('BYN', 'Looking for Super tasty oranges', 4000, 'MODERATED', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'BAG', 80.0, 50, 10, 'KILOGRAM'),
    ('BYN', 'I want Super delicious oranges', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Gomel', 'BASKET', 40.0, 100, 30, 'KILOGRAM'),
    ('BYN', 'I want Super delicious oranges', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Bordeaux', 'BUNCH', 400.0, 10, 30, 'KILOGRAM'),
    ('EUR', 'In need of Super old apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Paris', 'BUNCH', 6.666666666666667, 75, 30, 'PCS'),
    ('EUR', 'Willing to buy apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Paris', 'BAG', 50.0, 10, 10, 'PCS'),
    ('BYN', 'Yummy apples for needed', 4000, 'MODERATED', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Marseille', 'BUNCH', 160.0, 25, 10, 'KILOGRAM'),
    ('EUR', 'In need of Super old apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Marseille', 'BAG', 6.666666666666667, 75, 10, 'PCS'),
    ('EUR', 'Willing to buy apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Brest', 'BAG', 5.0, 100, 30, 'PCS'),
    ('BYN', 'I need mega big apples', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Gomel', 'BASKET', 160.0, 25, 30, 'KILOGRAM'),
    ('EUR', 'I need mega big apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Moscow', 'BASKET', 10.0, 50, 10, 'PCS'),
    ('USD', 'Looking for Super tasty oranges', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Gomel', 'BAG', 133.33333333333334, 75, 30, 'TON'),
    ('EUR', 'Mega yummy oranges needed', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Gomel', 'BOX', 10.0, 50, 30, 'PCS'),
    ('USD', 'In need of Super old apples', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Sochi', 'BOX', 200.0, 50, 10, 'TON'),
    ('BYN', 'Willing to buy apples', 4000, 'MODERATED', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'CRATE', 53.333333333333336, 75, 10, 'KILOGRAM'),
    ('USD', 'I need mega big apples', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Gomel', 'BUNCH', 100.0, 100, 10, 'TON'),
    ('BYN', 'I want Super delicious oranges', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Brest', 'BAG', 40.0, 100, 30, 'KILOGRAM'),
    ('BYN', 'Mega yummy oranges needed', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Saint-Petersburg', 'BOX', 40.0, 100, 30, 'KILOGRAM'),
    ('USD', 'Yummy apples for needed', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Brest', 'BOTTLE', 1000.0, 10, 10, 'TON'),
    ('USD', 'Willing to buy apples', 10000, 'ACTIVE', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Paris', 'CARTON', 133.33333333333334, 75, 30, 'TON'),
    ('USD', 'Looking for Super tasty oranges', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Gomel', 'BASKET', 133.33333333333334, 75, 10, 'TON'),
    ('EUR', 'Willing to buy apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Paris', 'SACK', 10.0, 50, 10, 'PCS'),
    ('USD', 'Mega yummy oranges needed', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Gomel', 'BUNCH', 400.0, 25, 10, 'TON'),
    ('USD', 'In need of Super old apples', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'BAG', 400.0, 25, 10, 'TON'),
    ('BYN', 'I want Super delicious oranges', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Paris', 'BOTTLE', 53.333333333333336, 75, 30, 'KILOGRAM'),
    ('EUR', 'Yummy apples for needed', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Gomel', 'BASKET', 20.0, 25, 10, 'PCS'),
    ('USD', 'I need mega big apples', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Bordeaux', 'BASKET', 133.33333333333334, 75, 10, 'TON'),
    ('BYN', 'Yummy apples for needed', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Paris', 'CRATE', 40.0, 100, 30, 'KILOGRAM'),
    ('EUR', 'In need of Super old apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Marseille', 'BOX', 10.0, 50, 30, 'PCS'),
    ('EUR', 'Willing to buy apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Paris', 'BUNCH', 20.0, 25, 30, 'PCS'),
    ('BYN', 'Looking for Super tasty oranges', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Minsk', 'SACK', 80.0, 50, 30, 'KILOGRAM'),
    ('EUR', 'In need of Super old apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'CARTON', 6.666666666666667, 75, 10, 'PCS'),
    ('BYN', 'In need of Super old apples', 4000, 'MODERATED', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Sochi', 'BASKET', 400.0, 10, 10, 'KILOGRAM'),
    ('BYN', 'Yummy apples for needed', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Marseille', 'BASKET', 160.0, 25, 30, 'KILOGRAM'),
    ('EUR', 'Mega yummy oranges needed', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Marseille', 'BUNCH', 6.666666666666667, 75, 10, 'PCS'),
    ('EUR', 'Yummy apples for needed', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Sochi', 'BUNCH', 10.0, 50, 10, 'PCS'),
    ('EUR', 'I want Super delicious oranges', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Paris', 'SACK', 5.0, 100, 10, 'PCS'),
    ('BYN', 'In need of Super old apples', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Paris', 'BAG', 40.0, 100, 30, 'KILOGRAM'),
    ('EUR', 'Yummy apples for needed', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Brest', 'BOTTLE', 6.666666666666667, 75, 30, 'PCS'),
    ('EUR', 'Mega yummy oranges needed', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Bordeaux', 'BUNCH', 50.0, 10, 10, 'PCS'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Saint-Petersburg', 'BUNCH', 5.0, 100, 30, 'PCS'),
    ('EUR', 'In need of Super old apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Sochi', 'BASKET', 6.666666666666667, 75, 30, 'PCS'),
    ('BYN', 'In need of Super old apples', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Gomel', 'CARTON', 53.333333333333336, 75, 30, 'KILOGRAM'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Minsk', 'SACK', 5.0, 100, 30, 'PCS'),
    ('EUR', 'I want Super delicious oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Brest', 'BOX', 10.0, 50, 30, 'PCS'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'BUNCH', 10.0, 50, 10, 'PCS'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Brest', 'BUNCH', 5.0, 100, 10, 'PCS'),
    ('EUR', 'Willing to buy apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Minsk', 'BASKET', 50.0, 10, 30, 'PCS'),
    ('EUR', 'Willing to buy apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Brest', 'BUNCH', 6.666666666666667, 75, 10, 'PCS'),
    ('BYN', 'Willing to buy apples', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Moscow', 'CRATE', 40.0, 100, 30, 'KILOGRAM'),
    ('BYN', 'I want Super delicious oranges', 4000, 'MODERATED', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Belarus', 'Minsk', 'BOTTLE', 53.333333333333336, 75, 10, 'KILOGRAM'),
    ('BYN', 'Willing to buy apples', 4000, 'MODERATED', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Moscow', 'SACK', 53.333333333333336, 75, 10, 'KILOGRAM'),
    ('BYN', 'Willing to buy apples', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Bordeaux', 'CARTON', 40.0, 100, 30, 'KILOGRAM'),
    ('BYN', 'I want Super delicious oranges', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Brest', 'BASKET', 40.0, 100, 30, 'KILOGRAM'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Moscow', 'BASKET', 10.0, 50, 30, 'PCS'),
    ('EUR', 'Willing to buy apples', 500, 'MODERATED', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'Russia', 'Saint-Petersburg', 'BAG', 10.0, 50, 10, 'PCS'),
    ('USD', 'I want Super delicious oranges', 10000, 'MODERATED', '2024-04-28 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'CENTIMETER', 'France', 'Bordeaux', 'SACK', 400.0, 25, 10, 'TON'),
    ('EUR', 'Looking for Super tasty oranges', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Gomel', 'BOTTLE', 50.0, 10, 30, 'PCS'),
    ('BYN', 'Looking for Super tasty oranges', 4000, 'ACTIVE', '2024-05-03 19:31:43.184000', null, 0, 9, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Russia', 'Sochi', 'BAG', 160.0, 25, 30, 'KILOGRAM'),
    ('EUR', 'In need of Super old apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'Belarus', 'Gomel', 'CRATE', 10.0, 50, 30, 'PCS'),
    ('EUR', 'Willing to buy apples', 500, 'ACTIVE', '2024-04-18 19:31:43.184000', null, 0, 7, 'e4f88498-7081-7075-66f7-1dc0b4128219', 'MILLIMETER', 'France', 'Bordeaux', 'BAG', 20.0, 25, 30, 'PCS');