package homeworks.homework09;

import homeworks.homework09.car.Car;
import homeworks.homework09.car.PerfomanceCar;
import homeworks.homework09.car.ShowCar;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] addons = {"Спойлер", "Аэродинамический обвес", "Улучшенные тормоза"};


        Car car1 = new Car("audi", 88, 100, 50, 250, 2011, "q6");
        PerfomanceCar car2 = new PerfomanceCar("bmw", 9, 152, 60, 300, 2008, "x5", addons);
        ShowCar car3 = new ShowCar ("lamborghini", 10, 200, 70, 500, 2022, "aventador", 10);
        Car[] cars = {car1, car2, car3};



        Race thisRace = new Race(1000, cars, "Круг", 1000);
        System.out.println(thisRace);

        // Создаем экземпляр гаража с нашим списком автомобилей
        Garage myGarage = new Garage(cars);

        while(true) {
            System.out.println("\nДоступные автомобили:");
            for (int i = 0; i < myGarage.getParcedCars().length; i++) {
                System.out.println(i + ": " + myGarage.getParcedCars()[i].toString());
            }

            System.out.print("Выберите номер автомобиля (-1 для выхода): ");
            int choice = scanner.nextInt(); // Чтение номера выбранного автомобиля
            scanner.nextLine(); // Потребление оставшегося символа новой строки

            if(choice == -1) break; // Выход из цикла

            if(choice >= 0 && choice < myGarage.getParcedCars().length) {
                myGarage.modificateCar(choice); // Изменяем выбранный автомобиль
            } else {
                System.out.println("Неверный выбор.");
            }
        }
    }

}
