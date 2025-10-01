INSERT INTO orders (customer_id, order_date, total_orders_count, discount_amount) VALUES
(10, NOW(), 2, 5.00),
(10, NOW() + INTERVAL '1 day', 3, 7.50),
(11, NOW(), 1, 3.00),
(12, NOW(), 1, 2.00);