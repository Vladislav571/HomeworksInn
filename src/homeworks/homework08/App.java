package homeworks.homework08;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        try {
            // Читаем данные из файла
            List<String> lines = Files.readAllLines(Path.of("input.txt"));

            // Списки для хранения покупателей и продуктов
            List<Person> persons = new ArrayList<>();
            List<Product> products = new ArrayList<>();

            // Первые две строки — это покупатели и продукты
            String[] personStrings = lines.get(0).split(";");
            String[] productStrings = lines.get(1).split(";");

            // Парсим покупателей
            for (String ps : personStrings) {
                Person p = new Person(ps);
                persons.add(p);
            }

            // Парсим продукты
            for (String pr : productStrings) {
                Product prod = new Product(pr);
                products.add(prod);
            }

            // Обрабатываем последующие строки как покупки
            for (int i = 2; i < lines.size() - 1; i++) {
                String purchase = lines.get(i);
                processPurchase(purchase, persons, products);
            }

            // Выводим результат в файл output.txt
            writeResultToFile(persons);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Обработка покупки
    private static void processPurchase(String purchase, List<Person> persons, List<Product> products) {
        // Регулярное выражение для отделения имени покупателя от продукта
        Pattern pattern = Pattern.compile("([^=]*)\\s+(.+)");
        Matcher matcher = pattern.matcher(purchase);

        if (!matcher.matches()) {
            System.out.println("Неверный формат строки: " + purchase);
            return;
        }

        // Имя покупателя — группа 1, название продукта — группа 2
        String personName = matcher.group(1).trim();
        String productName = matcher.group(2).trim();

        // Поиск покупателя и продукта
        Person buyer = findPerson(persons, personName);
        Product item = findProduct(products, productName);

        if (buyer != null && item != null) {
            if (buyer.buyProduct(item)) {
                System.out.println(buyer.getName() + " купил(-а) " + item.getName());
            } else {
                System.out.println(buyer.getName() + " не может позволить себе " + item.getName());
            }
        } else {
            System.out.println("Покупатель или продукт не найдены.");
        }
    }

    // Поиск покупателя по имени
    private static Person findPerson(List<Person> persons, String name) {
        for (Person p : persons) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        System.out.println("Покупатель '" + name + "' не найден в списке покупателей.");
        return null;
    }

    // Поиск продукта по названию
    private static Product findProduct(List<Product> products, String name) {
        for (Product p : products) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        System.out.println("Продукт '" + name + "' не найден в списке продуктов.");
        return null;
    }

    // Запись результата в файл
    private static void writeResultToFile(List<Person> persons) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        for (Person p : persons) {
            bw.write(p.toString() + "\n");
        }
        bw.close();
    }
}
