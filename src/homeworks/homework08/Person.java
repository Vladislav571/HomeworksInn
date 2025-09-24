package homeworks.homework08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Person {
    private String name;
    private double money;
    private List<Product> purchasedItems = new ArrayList<>();

    public Person(String data) {
        String[] parts = data.split("=");
        this.name = parts[0].trim();
        this.money = Double.parseDouble(parts[1].trim());
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    // Покупка продукта
    public boolean buyProduct(Product product) {
        if (money >= product.getPrice()) {
            purchasedItems.add(product);
            money -= product.getPrice();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (purchasedItems.isEmpty()) {
            return name + " - Ничего не куплено.";
        } else {
            return name + " - " + purchasedItems.stream().map(Product::getName).reduce((a, b) -> a + ", " + b).orElse("");
        }
    }
}
