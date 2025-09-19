package homeworks.homework06;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Читаем информацию о покупателях и продуктах
        String personsString = scanner.nextLine();
        String productsString = scanner.nextLine();

        // Разбираем на отдельные объекты
        String[] personStrings = personsString.split(";");
        String[] productStrings = productsString.split(";");

        // Создаем массивы для хранения покупателей и продуктов
        Person[] persons = new Person[personStrings.length];
        Product[] products = new Product[productStrings.length];

        // Заполняем массив покупателей
        for (int i = 0; i < personStrings.length; i++) {
            Person p = new Person(personStrings[i]); // Имя покупателя и сумма денег
            persons[i] = p;
        }

        // Заполняем массив продуктов
        for (int j = 0; j < productStrings.length; j++) {
            Product prod = new Product(productStrings[j]); // Название продукта и цена
            products[j] = prod;
        }

        // Начинаем цикл покупок
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("end")) break;

            // Индекс первого дефиса в строке
            int idx = line.indexOf('-');
            if (idx == -1) {
                System.out.println("Неверный формат строки: " + line);
                continue;
            }

            // Часть до дефиса — имя покупателя
            String personName = line.substring(0, idx).trim();
            // Всё после дефиса — название продукта
            String productName = line.substring(idx + 1).trim();

            // Начнём поиск покупателя и продукта
            boolean buyerFound = false;
            boolean productFound = false;

            for (Person person : persons) {
                if (person.getName().equals(personName)) {
                    buyerFound = true;
                    for (Product product : products) {
                        if (product.getName().equals(productName)) {
                            productFound = true;
                            if (person.buyProduct(product)) {
                                System.out.println(person.getName() + " купил(-а) " + product.getName());
                            } else {
                                System.out.println(person.getName() + " не может позволить себе " + product.getName());
                            }
                            break;
                        }
                    }
                    break;
                }
            }

            if (!buyerFound) {
                System.out.println("Покупатель '" + personName + "' не найден.");
            }
            if (!productFound) {
                System.out.println("Продукт '" + productName + "' не найден.");
            }
        }

        // Итоговый отчет по покупкам
        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }
}
