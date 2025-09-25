package homeworks.homework14;

import java.util.Arrays;
import java.util.Objects;

public class Person {
    private String name;
    private Double money;
    private Product[] products = new Product[0]; // Пакет с товарами

    public Person(String params) {
        if (params == null) {
            throw new IllegalArgumentException("Параметр не может быть null");
        }

        String[] paramArray = params.split("=", 2); // ограничиваем количество частей до двух
        this.name = paramArray[0].trim();
        this.money = Double.valueOf(paramArray[1].trim());

        if (paramArray.length < 2) {
            throw new IllegalArgumentException("Неверный формат параметра: " + params);
        }
        this.name = paramArray[0].trim(); // очищаем пробелы вокруг имени
        try {
            this.money = Double.parseDouble(paramArray[1].trim()); // пытаемся преобразовать сумму в число
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Ошибка преобразования суммы в число: " + paramArray[1], ex);
        }
    }

    public String getName() {
        return name;
    }

    public Double getMoney() {
        return money;
    }

    /**
     * Попробовать купить продукт
     *
     * @param product Продукт для покупки
     * @return true, если покупка прошла успешно, иначе false
     */
    public boolean buyProduct(Product product) {
        if (this.money >= product.getPrice()) {
            products = Arrays.copyOf(products, products.length + 1);
            products[products.length - 1] = product;
            this.money -= product.getPrice();
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(money, person.money) &&
                Arrays.deepEquals(products, person.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, Arrays.hashCode(products));
    }

    @Override
    public String toString() {
        if (products.length == 0) {
            return name + " - Ничего не куплено.";
        } else {
            return name + " - " + Arrays.toString(products);
        }
    }
}
