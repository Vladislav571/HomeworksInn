package ru.orel.java.homework09;

import lombok.*;
import ru.orel.java.homework09.car.Car;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class Race {
    private int distance;

    private String route;

    private int prizeFound;

    private Car[] members;


}
