package homeworks.homework07;

import java.time.LocalDate;

class DiscountProduct extends Product {
    private double discountPercentage;

    public DiscountProduct(String name, Double price, double discountPercentage) {
        super(name, price);
        this.discountPercentage = discountPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}

