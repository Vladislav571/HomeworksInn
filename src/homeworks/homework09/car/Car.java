package homeworks.homework09.car;

import java.util.Objects;

public class Car {
    private String brand;
    private String model;
    private int yearOfRelease;
    private int power;
    private  int boost; // ускорение
    private  int pendant; // подвеска
    private  int durability; // долговечность

    public Car(String brand, int durability, int pendant, int boost, int power, int yearOfRelease, String model) {
        this.brand = brand;
        this.durability = durability;
        this.pendant = pendant;
        this.boost = boost;
        this.power = power;
        this.yearOfRelease = yearOfRelease;
        this.model = model;
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getPendant() {
        return pendant;
    }

    public void setPendant(int pendant) {
        this.pendant = pendant;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return yearOfRelease == car.yearOfRelease && power == car.power && boost == car.boost && pendant == car.pendant && durability == car.durability && Objects.equals(brand, car.brand) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, yearOfRelease, power, boost, pendant, durability);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", power=" + power +
                ", boost=" + boost +
                ", pendant=" + pendant +
                ", durability=" + durability +
                '}';
    }
}
