package homeworks.homework09.car;

import java.util.Arrays;
import java.util.Objects;

public class PerfomanceCar extends Car{
    private String[] addons;

    public PerfomanceCar(String brand, int durability, int pendant, int boost, int power, int yearOfRelease, String model, String[] addons) {
        super(brand, durability, (int)(pendant * 0.75), boost, (int)(power * 1.5), yearOfRelease, model);
        this.addons = addons;
    }

    @Override
    public void setPower(int power) {
        super.setPower((int) (power * 1.5));
    }

    @Override
    public void setPendant(int pendant) {
        super.setPendant((int) (pendant * 0.75));
    }

    public String[] getAddons() {
        return addons;
    }

    public void setAddons(String[] addons) {
        this.addons = addons;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerfomanceCar that = (PerfomanceCar) o;
        return Objects.deepEquals(addons, that.addons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(addons));
    }


    @Override
    public String toString() {
        return "PerfomanceCar{" +
                "brand='" + super.getBrand() + '\'' +
                ", model='" + super.getModel() + '\'' +
                ", yearOfRelease=" + super.getYearOfRelease() +
                ", power=" + super.getPower() +
                ", boost=" + super.getBoost() +
                ", pendant=" + super.getPendant() +
                ", durability=" + super.getDurability() +
                ", addons=" + Arrays.toString(addons) +
                '}';
    }
}
