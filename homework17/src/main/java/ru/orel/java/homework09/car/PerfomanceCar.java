package ru.orel.java.homework09.car;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class PerfomanceCar extends Car{
    private String[] addons;

}
