package homeworks.homework11;

import java.util.Objects;

public class Car {

    private String numberAuto;
    private String model;
    private String colour;
    private double mileage;
    private int price;

    public Car(String numberAuto, int price, String colour, double mileage, String model) {
        this.numberAuto = numberAuto;
        this.price = price;
        this.colour = colour;
        this.mileage = mileage;
        this.model = model;
    }

    public Car() {
    }

    public String getNumberAuto() {
        return numberAuto;
    }

    public void setNumberAuto(String numberAuto) {
        this.numberAuto = numberAuto;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
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
        return Double.compare(mileage, car.mileage) == 0 && price == car.price && Objects.equals(numberAuto, car.numberAuto) && Objects.equals(model, car.model) && Objects.equals(colour, car.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberAuto, model, colour, mileage, price);
    }

    @Override
    public String toString() {
        return "Car{" +
                "numberAuto='" + numberAuto + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                '}';
    }
}
