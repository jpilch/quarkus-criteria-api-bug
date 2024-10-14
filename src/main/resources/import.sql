INSERT INTO item(id, colors, color)
VALUES
    (nextval('item_seq'), '{"RED", "GREEN", "BLUE"}', 'RED'),
    (nextval('item_seq'),'{"RED"}', 'BLUE'),
    (nextval('item_seq'),'{"GREEN","BLUE"}', 'BLUE');
