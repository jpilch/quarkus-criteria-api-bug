CREATE SEQUENCE item_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE item (
    id BIGINT DEFAULT nextval('item_seq') PRIMARY KEY,
    colors color[]
)
