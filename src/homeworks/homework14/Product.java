package homeworks.homework14;

import java.util.Objects;

public class Product {
    private String name;
    private Double price;

    public Product(String params) {
        String[] paramArray = params.split("=", 2); // ограничиваем количество частей до двух
        if (paramArray.length < 2) {
            throw new IllegalArgumentException("Неверный формат продукта: " + params);
        }
        this.name = paramArray[0].trim(); // очищаем пробелы вокруг названия товара
        try {
            double priceValue = Double.parseDouble(paramArray[1].trim()); // проверяем цену
            if (priceValue <= 0) {
                throw new IllegalArgumentException("Цена должна быть положительной: " + paramArray[1]);
            }
            this.price = priceValue;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Ошибка преобразования цены в число: " + paramArray[1], ex);
        }
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return name;
    }
}
