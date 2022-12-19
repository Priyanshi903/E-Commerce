-- dummy data for vendor

INSERT INTO vendor(vendor_id,vendor_name,delivery_charge,rating) VALUES ('001', 'siva', 30, 3);
INSERT INTO vendor(vendor_id,vendor_name,delivery_charge,rating) VALUES ('002', 'priyanshi', 60, 5);
INSERT INTO vendor(vendor_id,vendor_name,delivery_charge,rating) VALUES ('003', 'chandrakanth', 80, 5);
INSERT INTO vendor(vendor_id,vendor_name,delivery_charge,rating) VALUES ('004', 'jessee', 40, 2);

-- dummy data for vendor_stock

INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-010', '001', 24, '2022-03-26');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-011', '002', 30, '2022-04-26');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-012', '003', 23, '2022-04-01');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-013', '004', 14, '2022-05-26');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-014', '001', 0, '2022-02-26');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-015', '002', 74, '2022-04-15');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-016', '003', 20, '2022-02-26');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-017', '004', 10, '2022-05-28');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-018', '001', 40, '2022-02-26');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-019', '002', 30, '2022-02-26');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-020', '003', 20, '2022-09-15');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-021', '004', 15, '2022-11-22');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-010', '004', 22, '2022-12-01');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-011', '003', 22, '2022-09-19');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-012', '002', 22, '2022-01-26');
INSERT INTO vendor_stock(product_id,vendor_id,stock_in_hand,replinshment_date) VALUES ('product-013', '001', 22, '2022-02-26');




