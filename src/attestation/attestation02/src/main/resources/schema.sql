-- Таблица 'Product' хранит информацию о товарах
CREATE TABLE IF NOT EXISTS Product (
    product_id SERIAL PRIMARY KEY,
    description TEXT,
    price NUMERIC(10, 2),
    quantity INTEGER
);

-- Таблица 'Customer' хранит информацию о покупателях
CREATE TABLE IF NOT EXISTS Customer (
    customer_id SERIAL PRIMARY KEY,
    full_name TEXT
);

-- Таблица 'Order' связывает товары и покупателей, хранит информацию о заказах
CREATE TABLE IF NOT EXISTS Orders (
    order_id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES Product(product_id),
    customer_id INTEGER REFERENCES Customer(customer_id),
    order_date DATE,
    item_count INTEGER
);

-- Добавляем данные в таблицу 'Product'
INSERT INTO Product (description, price, quantity) VALUES
('Телефон', 10000.00, 10),
('Лаптоп', 30000.00, 5),
('Планшет', 15000.00, 8),
('Минатор', 8000.00, 12),
('Стерео', 3000.00, 20),
('PS5', 25000.00, 3),
('USB-флешка', 500.00, 50),
('Компьютерная мышь', 1000.00, 30),
('Клавиатура', 2000.00, 40),
('Наушники', 4000.00, 25);

-- Добавляем данные в таблицу 'Customer'
INSERT INTO Customer (full_name) VALUES
('Максимов Илья'),
('Агаев Владислав'),
('Петрова Петра'),
('Майкл Оуэн'),
('Екатерина Мизулина'),
('Васина Александра'),
('Никитин Владимир'),
('Неделин Дмитрий'),
('Аплачкина Светлана'),
('Иванова Иванита');

-- Добавляем данные в таблицу 'Orders'
INSERT INTO Orders (product_id, customer_id, order_date, item_count) VALUES

(2, 2, '2021-02-15', 1),
(3, 3, '2024-03-10', 3),
(4, 4, '2023-04-20', 2),
(5, 5, '2023-05-05', 4),
(6, 6, '2019-06-12', 1),
(7, 7, '2023-07-18', 5),
(8, 8, '2022-08-25', 3),
(9, 9, '2025-09-30', 2),
(10, 10, '2025-10-15', 1);