package ru.orel.java.homework09;


import lombok.*;
import ru.orel.java.homework09.car.Car;

import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Garage {
    private Car[] parcedCars;

    public void modifyCar(int carIndex) {
        Car car = parcedCars[carIndex];
        System.out.println(car.toString());
        System.out.println("Выберите какой параметр хотите изменить: ");
        Scanner scanner = new Scanner(System.in);

        switch (scanner.nextLine()) {
            case "мощность":
                System.out.println("Введите новое значение мощности: ");
                int newPower = scanner.nextInt();
                car.setPower(newPower);
                break;
            case "ускорение":
                System.out.println("Введите новое значение ускорения: ");
                int newBoost = scanner.nextInt();
                car.setBoost(newBoost);
                break;
            default:
                System.out.println("Выбран неверный параметр");
        }
    }
}
