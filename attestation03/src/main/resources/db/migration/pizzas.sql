CREATE TABLE IF NOT EXISTS pizzas (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO pizzas (name, description, price) VALUES
    ('Сицилия', 'Чесночный соус, русский, базилик', 450.00),
    ('Пепперони', 'Сырный соус, моцарелла, пепперони', 550.00),
    ('Четыре сыра', 'Моцарелла, пармезан, горгонзола, фета', 650.00),
    ('Итальянская', 'Томатный соус, сыр, ветчина, перец халапеньо', 600.00);
