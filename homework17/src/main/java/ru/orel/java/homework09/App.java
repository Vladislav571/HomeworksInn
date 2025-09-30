package ru.orel.java.homework09;

import ru.orel.java.homework09.car.Car;
import ru.orel.java.homework09.repositories.CarRepository;
import ru.orel.java.homework09.repositories.CarRepositoryFileImpl;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Указываем файл данных
        File carsDataFile = new File("C:\\Users\\Владислав\\HomeworksInn\\homework17\\src\\main\\resources\\cars.txt");

        // Репозиторий для работы с файлом
        CarRepository repository = new CarRepositoryFileImpl(carsDataFile);

        try {
            // Получаем список автомобилей
            List<Car> cars = repository.findAll();

            // Преобразуем список в массив
            Car[] carArray = cars.toArray(new Car[cars.size()]);

            // Гараж с машинами
            Garage myGarage = new Garage(carArray);

            while (true) {
                System.out.println("\nДоступные автомобили:");
                for (int i = 0; i < myGarage.getParcedCars().length; i++) {
                    System.out.println(i + ": " + myGarage.getParcedCars()[i].toString());
                }

                System.out.print("Выберите номер автомобиля (3 для выхода): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Потеря символа новой строки

                if (choice == 3) break;

                if (choice >= 0 && choice < myGarage.getParcedCars().length) {
                    myGarage.modifyCar(choice);
                } else {
                    System.out.println("Неверный выбор.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
