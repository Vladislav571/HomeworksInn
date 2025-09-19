package homeworks.homework03;

import java.time.LocalDate;

public class TV {
    private String name;// Производитель
    private int inches; // Дюймы
    private double price; // Цена
    private LocalDate hireDate; // Дата покупки
    private Shop shop;

    public TV(String name, int inches, double price, LocalDate hireDate, Shop shop){
        this.name = name;
        this.inches = inches;
        this.price = price;
        this.hireDate = hireDate;
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInches() {
        return inches;
    }

    public void setInches(int inches) {
        this.inches = inches;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "TV{" +
                "name='" + name + '\'' +
                ", inches=" + inches +
                ", price=" + price +
                ", hireDate=" + hireDate +
                ", shop=" + shop +
                '}';
    }
}
