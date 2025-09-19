package homeworks.homework09.car;

import java.util.Objects;

public class ShowCar extends Car{
    private int stars = 0; // поле для оценки популярности автомобиля

    public ShowCar(String brand, int durability, int pendant, int boost, int power, int yearOfRelease, String model, int stars) {
        super(brand, durability, pendant, boost, power, yearOfRelease, model);
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ShowCar showCar = (ShowCar) o;
        return stars == showCar.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stars);
    }

    @Override
    public String toString() {
        return "ShowCar{" +
                "stars=" + stars +
                '}';
    }
}
