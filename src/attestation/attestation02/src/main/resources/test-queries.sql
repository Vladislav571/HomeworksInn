-- Чтение данных (Select)         


-- 1. Просмотр всех товаров
SELECT * FROM Product;

-- 2. Показать конкретного покупателя по ID
SELECT * FROM Customer WHERE customer_id = 1;

-- 3. Просмотр всех заказов конкретного покупателя
SELECT * FROM Orders WHERE customer_id = 1;

-- 4. Просмотр подробной информации обо всех заказах вместе с товарами и клиентами
SELECT P.description, C.full_name, O.order_date, O.item_count
FROM Product P
JOIN Orders O ON P.product_id = O.product_id
JOIN Customer C ON O.customer_id = C.customer_id;


--  Изменение данных (Update / Insert)


-- 5. Обновление цены товара с указанным ID
UPDATE Product
SET price = 12000.00
WHERE product_id = 1;

-- 6. Изменение количества купленных товаров в заказе
UPDATE Orders
SET item_count = 5
WHERE order_id = 1;

-- 7. Добавление нового товара
INSERT INTO Product(description, price, quantity)
VALUES ('Смартфон', 20000.00, 15);


-- Удаление данных (Delete)

-- 8. Удаление всех заказов конкретного покупателя
DELETE FROM Orders
WHERE customer_id = 1;

-- 9. Удаляем все заказы, связанные с товаром с product_id = 1
DELETE FROM Orders WHERE product_id = 1;

-- Теперь удаляем сам товар
DELETE FROM Product WHERE product_id = 1;

-- Смотрим на изменённые данные
SELECT P.*, C.*, O.*
FROM Product P
LEFT JOIN Orders O ON P.product_id = O.product_id
LEFT JOIN Customer C ON O.customer_id = C.customer_id;