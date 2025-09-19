package homeworks.homework06;

import java.util.Objects;

public class Product {
    private String name;
    private Double price;

    public Product(String params) {
        String[] paramArray = params.split("=", 2); // Ограничиваем количество фрагментов до 2-х
        if (paramArray.length < 2) {
            throw new IllegalArgumentException("Неверный формат продукта: " + params);
        }
        this.name = paramArray[0].trim();
        this.price = Double.valueOf(paramArray[1].trim());
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
