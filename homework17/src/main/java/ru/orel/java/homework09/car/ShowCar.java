package ru.orel.java.homework09.car;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ShowCar extends Car {
    private int stars; // Популярность автомобиля
}
