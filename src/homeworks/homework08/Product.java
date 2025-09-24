package homeworks.homework08;

import java.util.Objects;

class Product {
    private String name;
    private double price;

    public Product(String data) {
        String[] parts = data.split("=");
        this.name = parts[0].trim();
        this.price = Double.parseDouble(parts[1].trim());
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
