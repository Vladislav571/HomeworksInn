SELECT first_name, last_name FROM customers;
SELECT o.order_id, c.first_name, c.last_name, o.order_date, o.total_orders_count, o.discount_amount
FROM orders o
INNER JOIN customers c ON o.customer_id = c.id;