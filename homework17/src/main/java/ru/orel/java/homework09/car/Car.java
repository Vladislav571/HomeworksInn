package ru.orel.java.homework09.car;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    private String brand;
    private String model;
    private int yearOfRelease;
    private int power;
    private int boost; // ускорение
    private int pendant; // подвеска
    private int durability; // долговечность

    @Override
    public String toString() {
        return brand + "," + model + "," + yearOfRelease + "," + power + "," + boost + "," + pendant + "," + durability;
    }
}
