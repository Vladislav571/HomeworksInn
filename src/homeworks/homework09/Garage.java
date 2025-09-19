package homeworks.homework09;

import homeworks.homework09.car.Car;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Garage {
    private Car[] parcedCars;

    public Garage(Car[] parcedCars) {
        this.parcedCars = parcedCars;
    }

    public Garage() {
    }

    public void modificateCar(int carNumber){
        Car car = parcedCars[carNumber];
        System.out.println(car.toString());
        System.out.println("Выберите какой параметр хотите изменить: ");
        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextLine()){
            case "мощность" ->{
                System.out.println("Введите новое значение мощности: ");
                int newPower  = scanner.nextInt();
                car.setPower(newPower);
            }
            case "ускорение" ->{
                System.out.println("Введите новое значение ускорения: ");
                int newBoost = scanner.nextInt();
                car.setBoost(newBoost);
            }

            default -> System.out.println("Выбран неверный параметр");

        }
    }

    public Car[] getParcedCars() {
        return parcedCars;
    }

    public void setParcedCars(Car[] parcedCars) {
        this.parcedCars = parcedCars;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Garage garage = (Garage) o;
        return Objects.deepEquals(parcedCars, garage.parcedCars);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(parcedCars);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "parcedCars=" + Arrays.toString(parcedCars) +
                '}';
    }
}
