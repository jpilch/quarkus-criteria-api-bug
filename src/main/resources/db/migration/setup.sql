CREATE TYPE color AS ENUM (
    'RED',
    'GREEN',
    'BLUE'
);

CREATE SEQUENCE item_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE item (id BIGINT PRIMARY KEY, colors color[], color color)
