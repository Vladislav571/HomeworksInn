package homeworks.homework11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Car car = new Car("А225СА57", 500000, "Black", 3500.55,"Audi" );

        System.out.println(car);

        List<Car> cars = Arrays.asList(
                new Car("А225СА57", 500000, "Black", 3500.55, "Audi"),
                new Car("X123ОP16", 1000000, "Red", 10000, "BMW"),
                new Car("Д116ЖА99", 800000, "Blue", 0, "Toyota"),
                new Car("А052ВА52", 1200000, "Green", 5000, "Tesla")
        );


        final String colorToFind = "Black";
        final double mileageToFind = 0;


        List<String> foundCarsNumbers = cars.stream()
                .filter(c -> c.getColour().equals(colorToFind) ||
                        c.getMileage() == mileageToFind)
                .map(Car::getNumberAuto)
                .collect(Collectors.toList());

        System.out.println("Номера найденных автомобилей: " + foundCarsNumbers);


        final int minPrice = 500000;
        final int maxPrice = 1200000;


        long uniqueModelsCount = cars.stream()
                .filter(c -> c.getPrice() >= minPrice && c.getPrice() <= maxPrice)
                .map(Car::getModel)
                .distinct()
                .count();

        System.out.println("Количество уникальных моделей в ценовом диапазоне [" + minPrice + "-" + maxPrice + "] : " + uniqueModelsCount);

    }
}
